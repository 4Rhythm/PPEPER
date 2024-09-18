package src.boj.september.Problem21924

import java.util.LinkedList
import java.util.PriorityQueue

private lateinit var graph: Array<LinkedList<Pair<Int, Int>>>
private lateinit var visited: BooleanArray
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    graph = Array(n + 1) { LinkedList() }
    visited = BooleanArray(n + 1)
    var roadSum = 0L
    repeat(m) {
        val (a, b, c) = readLine().split(" ").map { it.toInt() }
        graph[a].add(Pair(b, c))
        graph[b].add(Pair(a, c))
        roadSum += c
    }
    var connectedSum = 0L
    val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
    pq.add(Pair(1, 0))
    while (pq.isNotEmpty()) {
        val (to, cost) = pq.poll()
        if (visited[to]) continue

        visited[to] = true
        connectedSum += cost
        for (nextNode in graph[to]) {
            if (!visited[nextNode.first]) {
                pq.offer(nextNode)
            }
        }
    }
    if (visited.count { !it } != 1) println(-1) else println(roadSum - connectedSum)
}