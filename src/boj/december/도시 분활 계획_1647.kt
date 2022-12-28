package boj.december

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

data class Area(val end: Int, val weight: Int)
private lateinit var graph: Array<LinkedList<Area>>
private lateinit var visited: BooleanArray
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var sum = 0
    val (N, M) = readLine().split(' ').map { it.toInt() }
    var maxEdge = 0
    graph = Array(N + 1) { LinkedList() }
    visited = BooleanArray(N + 1)
    repeat(M) {
        val (a, b, w) = readLine().split(' ').map { it.toInt() }
        graph[a].add(Area(b, w))
        graph[b].add(Area(a, w))
    }
    val pq = PriorityQueue<Area>(compareBy { it.weight })
    pq.offer(Area(1,0))
    while (pq.isNotEmpty()) {
        val curr = pq.poll()
        if (!visited[curr.end]) {
            visited[curr.end] = true
            sum += curr.weight
            maxEdge = maxEdge.coerceAtLeast(curr.weight)
            for (nextNode in graph[curr.end]) {
                if (!visited[nextNode.end]) {
                    pq.offer(nextNode)
                }
            }
        }
    }
    println(sum - maxEdge)
}