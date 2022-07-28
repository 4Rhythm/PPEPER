package boj.july

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val size = readLine().toInt()
    // 1. 인접 행렬로 구현
    val graph = Array(size) { IntArray(size) {0} }
    val number = readLine().toInt()
    val visited = BooleanArray(size)
    // 그래프 init
    for (i in 0 until number) {
        val input = readLine().split(' ').map { it.toInt() }
        graph[input[0] - 1][input[1] - 1] = 1
        graph[input[1] - 1][input[0] - 1] = 1
    }
    println(bfs(0, graph, visited))
}

private fun bfs(start: Int, graph: Array<IntArray>, visited: BooleanArray): Int {
    val list = ArrayList<Int>()
    val queue: Queue<Int> = LinkedList()
    // 시작노드
    queue.add(start)
    visited[start] = true
    while (queue.isNotEmpty()) {
        val node = queue.poll()
        list.add(node)
        // node와 연결된 인접 노드 확인
        for (i in graph.indices) {
            // 연결된 방문이 안되어있는 노드 확인
            if (graph[node][i] == 1 && !visited[i]) {
                queue.add(i)
                visited[i] = true
            }
        }
    }
    return list.size - 1
}
