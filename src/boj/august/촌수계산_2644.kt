package boj.august

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private var answer = -1
fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val people = readLine().toInt()
    val graph = Array(people) { LinkedList<Int>() }
    val str = readLine().split(' ').map { it.toInt() }
    val from = str[0] - 1
    val to = str[1] - 1
    for (i in 0 until readLine().toInt()) {
        val input = readLine().split(' ').map { it.toInt() }
        graph[input[0] - 1].add(input[1] - 1)
        graph[input[1] - 1].add(input[0] - 1)
    }
    val visited = BooleanArray(people)
    visited[from] = true
    find(graph, visited, 0, from, to)
    println(answer)
}

private fun find(graph: Array<LinkedList<Int>>, visited: BooleanArray, count: Int, from: Int, to: Int) {
    // 확인하고 싶은 촌수
    if (from == to) {
        answer = count
        return
    }
    for (adj in graph[from]) {
        if (!visited[adj]) {
            visited[adj] = true
            find(graph, visited, count + 1, adj, to)
        }
    }
}
