package boj.december

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var roadSum = 0L
    val (N, M) = readLine().split(' ').map { it.toInt() }
    val graph = Array(N + 1) { LinkedList<Pair<Int, Int>>() }
    repeat(M) {
        val (a, b, c) = readLine().split(' ').map { it.toInt() }
        graph[a].add(Pair(b, c))
        graph[b].add(Pair(a, c))
        roadSum += c
    }
    val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
    val visited = BooleanArray(N + 1)
    pq.offer(Pair(1, 0))
    var connected = 0L
    while (pq.isNotEmpty()) {
        val curr = pq.poll()
        if (!visited[curr.first]) {
            visited[curr.first] = true
            connected += curr.second
            for (nextNode in graph[curr.first]) {
                if (!visited[nextNode.first]) {
                    pq.offer(nextNode)
                }
            }
        }
    }
    // 그래프가 아니면 -1 출력
    if (visited.count { !it } != 1) println(-1) else println(roadSum - connected)
}