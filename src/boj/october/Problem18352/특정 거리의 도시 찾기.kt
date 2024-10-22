package src.boj.october.Problem18352

import java.util.*

private lateinit var graph: Array<LinkedList<Pair<Int, Int>>>
private lateinit var dist: IntArray
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m, k, x) = readLine().split(" ").map { it.toInt() }
    graph = Array(n + 1) { LinkedList() }
    dist = IntArray(n + 1) { Int.MAX_VALUE }
    repeat(m) {
        val (from, to) = readLine().split(" ").map { it.toInt() }
        graph[from].add(Pair(to, 1))
    }
    dijkstra(x)
    if (dist.none { it == k }) {
        println(-1)
    } else {
        dist.forEachIndexed { index, dist ->
            if (dist == k) println(index)
        }
    }
}

private fun dijkstra(x: Int) {
    val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
    pq.add(Pair(x, 0))
    dist[x] = 0
    while (pq.isNotEmpty()) {
        val (node, nodeDistance) = pq.poll()
        if (dist[node] < nodeDistance) continue
        for ((nextNode, nextDistance) in graph[node]) {
            val newDistance = nodeDistance + nextDistance
            if (newDistance < dist[nextNode]) {
                dist[nextNode] = newDistance
                pq.add(Pair(nextNode, newDistance))
            }
        }
    }
}
