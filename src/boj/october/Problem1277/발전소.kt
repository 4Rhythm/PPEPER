package src.boj.october.Problem1277

import java.util.*
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

private const val INF = 987654321.0
private lateinit var graph: Array<LinkedList<Pair<Int, Double>>>
private lateinit var dist: DoubleArray
fun main() = with(System.`in`.bufferedReader()) {
    val (n, w) = readLine().split(" ").map { it.toInt() }
    val m = readLine().toDouble()
    dist = DoubleArray(n + 1) { INF }
    graph = Array(n + 1) { LinkedList() }
    val nodes = Array(n) { readLine().split(" ").map { it.toInt() }}
    repeat(w) {
        val (a, b) = readLine().split(" ").map { it.toInt() }
        graph[a].add(Pair(b, 0.0))
        graph[b].add(Pair(a, 0.0))
    }
    for (i in nodes.indices) {
        val from = nodes[i]
        for (j in i + 1 until nodes.size) {
            val dist = distance(from, nodes[j])
            if (dist <= m) {
                graph[i + 1].add(Pair(j + 1, dist))
                graph[j + 1].add(Pair(i + 1, dist))
            }
        }
    }
    // 최단거리 구하기
    val pq = PriorityQueue<Pair<Int, Double>>(compareBy { it.second })
    pq.add(Pair(1, 0.0))
    while (pq.isNotEmpty()) {
        val (node, weight) = pq.poll()
        if (dist[node] < weight) continue

        for ((nextNode, nextWeight) in graph[node]) {
            if (weight + nextWeight < dist[nextNode]) {
                dist[nextNode] = weight + nextWeight
                pq.add(Pair(nextNode, weight + nextWeight))
            }
        }
    }
    println((dist[n] * 1000).toInt())
}

private fun distance(from: List<Int>, to: List<Int>): Double {
    return sqrt(abs(from[0] - to[0]).toDouble().pow(2.0) + abs(from[1] - to[1]).toDouble().pow(2.0))
}