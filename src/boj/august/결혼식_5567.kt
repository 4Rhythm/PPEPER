package boj.august

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val size = readLine().toInt()
    val graph = Array(size) { LinkedList<Int>() }
    for (i in 0 until readLine().toInt()) {
        val input = readLine().split(' ').map { it.toInt() }
        graph[input[0] - 1].add(input[1] - 1)
        graph[input[1] - 1].add(input[0] - 1)
    }
    println(invite(graph))
}

private fun invite(graph: Array<LinkedList<Int>>): Int {
    var count = 0
    val visited = IntArray(graph.size)
    val queue: Queue<Int> = LinkedList()
    queue.offer(0)
    visited[0] = 1
    while (queue.isNotEmpty()) {
        val v = queue.poll()
        // 친구의 친구 관계를 넘어섬
        if (2 < visited[v]) {
            continue
        }
        // 주변 친구 확인
        for (adj in graph[v]) {
            if (visited[adj] == 0) {
                queue.offer(adj)
                visited[adj] = visited[v] + 1
                count++
            }
        }
    }
    return count
}
