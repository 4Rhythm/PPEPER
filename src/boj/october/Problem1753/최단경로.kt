package src.boj.october.Problem1753

import java.util.*

private const val INF = 200001
private lateinit var graph: Array<LinkedList<Pair<Int, Int>>>
private lateinit var dist: IntArray
fun main() = with(System.`in`.bufferedReader()) {
    val (v, e) = readLine().split(" ").map { it.toInt() }
    val k = readLine().toInt()
    dist = IntArray(v + 1) { INF }
    graph = Array(v + 1) { LinkedList() }
    repeat(e) {
        val (a, b, c) = readLine().split(" ").map { it.toInt() }
        graph[a].add(Pair(b, c))
    }
    dist[k] = 0
    val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
    pq.add(Pair(k, 0))
    while (pq.isNotEmpty()) {
        val (node, cost) = pq.poll()
        if (dist[node] < cost) continue

        for ((nextNode, nextCost) in graph[node]) {
            if (nextCost + cost < dist[nextNode]) {
                dist[nextNode] = nextCost + cost
                pq.add(Pair(nextNode, nextCost + cost))
            }
        }
    }
    dist.takeLast(v)
        .map { if (it == INF) "INF" else it }
        .forEach(::println)
}