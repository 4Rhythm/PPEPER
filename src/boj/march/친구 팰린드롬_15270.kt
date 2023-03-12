package boj.march

import java.util.*

private lateinit var graph: Array<LinkedList<Int>>
private lateinit var visited: BooleanArray
private var answer = 0
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    graph = Array(n + 1) { LinkedList() }
    visited = BooleanArray(n + 1)
    repeat(m) {
        val input = readLine().split(" ").map { it.toInt() }
        graph[input[0]].add(input[1])
        graph[input[1]].add(input[0])
    }
    goToStage(1, 0, n)
    if (answer < n) answer++
    println(answer)
}

private fun goToStage(i: Int, conn: Int, n: Int) {
    answer = answer.coerceAtLeast(conn)
    if (n < i) return
    if (visited[i]) {
        goToStage(i + 1, conn, n)
    } else {
        visited[i] = true
        for (adj in graph[i]) {
            if (!visited[adj]) {
                visited[adj] = true
                goToStage(i + 1, conn + 2, n)
                visited[adj] = false
            }
        }
        visited[i] = false
        goToStage(i + 1, conn, n)
    }
}
