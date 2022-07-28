package boj.july

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val size = readLine().toInt()
    // 2. 인접 리스트로 구현
    val adjList = Array(size) { LinkedList<Int>() }
    val number = readLine().toInt()
    val visited = BooleanArray(size)
    // 그래프 init
    for (i in 0 until number) {
        val input = readLine().split(' ').map { it.toInt() }
        adjList[input[0] - 1].add(input[1] - 1)
        adjList[input[1] - 1].add(input[0] - 1)
    }
    println(bfs2(0, adjList, visited))
}

private fun bfs2(start: Int, adjList: Array<LinkedList<Int>>, visited: BooleanArray): Int {
    val list = ArrayList<Int>()
    val queue: Queue<Int> = LinkedList()
    // 시작노드
    queue.add(start)
    visited[start] = true
    while (queue.isNotEmpty()) {
        val node = queue.poll()
        list.add(node)
        for (adjNode in adjList[node]) {
            // 방문하지 않았음
            if (!visited[adjNode]) {
                queue.add(adjNode)
                visited[adjNode] = true
            }
        }
    }
    return list.size - 1
}
