package boj.july

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val size = readLine().toInt()
    val graph = Array(size) { IntArray(size) { 0 } }
    val adjList = Array(size) { LinkedList<Int>() }
    for (i in 0 until size) {
        val input = readLine().split(' ').map { it.toInt() }
        for (j in input.indices) {
            if (input[j] == 1) {
                adjList[i].add(j)
            }
        }
    }
    // 노드 연결관계 확인 -> bfs
    for (start in graph.indices) {
        val visited = BooleanArray(graph.size)
        val list = checkNode(start, adjList, visited)
        list.forEach {
            graph[start][it] = 1
        }
    }
    println("-------------------------")
    // 출력
    for (i in graph.indices) {
        for (j in graph.indices) {
            print("${graph[i][j]} ")
        }
        println()
    }
}

fun checkNode(start: Int, adjList: Array<LinkedList<Int>>, visited: BooleanArray): ArrayList<Int> {
    val list = ArrayList<Int>()
    val queue: Queue<Int> = LinkedList()
    // 확인하려는 노드
    queue.offer(start)
    while (queue.isNotEmpty()) {
        val node = queue.poll()
        list.add(node)
        // 인접 리스트확인
        for (adjNode in adjList[node]) {
            if (!visited[adjNode]) {
                queue.offer(adjNode)
                visited[adjNode] = true
            }
        }
    }
    // 자기 자신은 제거
    list.removeAt(0)
    return list
}
