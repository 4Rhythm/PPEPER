package boj.august

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val input = readLine().split(' ').map { it.toInt() }
    val graph = Array(input[0]) { LinkedList<Int>() }
    // 간선 init
    for (i in 0 until input[1]) {
        val v = readLine().split(' ').map { it.toInt() }
        graph[v[0] - 1].add(v[1] - 1)
        graph[v[1] - 1].add(v[0] - 1)
    }
    // 시작 노드
    val start = input[2] - 1
    val sortedDescendingGraph = graph.map {
        it.sortedDescending()
    }
    val sortedGraph = graph.map {
        it.sorted()
    }
    dfs(start, sortedDescendingGraph)
    println()
    bfs(start, sortedGraph)
}

private fun bfs(start: Int, graph: List<List<Int>>) {
    val visited = BooleanArray(graph.size)
    val queue: Queue<Int> = LinkedList()
    queue.offer(start)
    visited[start] = true
    while (queue.isNotEmpty()) {
        val v = queue.poll()
        print("${v + 1} ")
        for (adjNode in graph[v]) {
            if (!visited[adjNode]) {
                queue.offer(adjNode)
                visited[adjNode] = true
            }
        }
    }
}

private fun dfs(start: Int, graph: List<List<Int>>) {
    val visited = BooleanArray(graph.size)
    val stack: Stack<Int> = Stack()
    stack.push(start)
    while (stack.isNotEmpty()) {
        val v = stack.pop()
        if (!visited[v]) {
            print("${v + 1} ")
            visited[v] = true
            for (adjNode in graph[v]) {
                if (!visited[adjNode]) {
                    stack.push(adjNode)
                }
            }
        }
    }
}
