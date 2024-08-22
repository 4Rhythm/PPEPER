package src.boj.august.Problem1303

import java.util.LinkedList

enum class Type(
    val ch: Char
) {
    WHITE('W'), BLACK('B')
}
private lateinit var graph: Array<CharArray>
private lateinit var visited: Array<BooleanArray>
private lateinit var answer: IntArray
private val dx = intArrayOf(1, -1, 0, 0)
private val dy = intArrayOf(0, 0, 1, -1)
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    graph = Array(m) { readLine().toCharArray() }
    visited = Array(m) { BooleanArray(n) }
    answer = IntArray(2)
    for (i in graph.indices) {
        for (j in graph[0].indices) {
            if (!visited[i][j]) {
                bfs(i, j)
            }
        }
    }
    println(answer.joinToString(" "))
}

private fun bfs(i: Int, j: Int) {
    val type = if (graph[i][j] == Type.WHITE.ch) Type.WHITE else Type.BLACK
    var count = 0
    val q = LinkedList<Pair<Int, Int>>()
    q.add(Pair(i, j))
    visited[i][j] = true
    while (q.isNotEmpty()) {
        val (x, y) = q.poll()
        count++
        for (i in 0 until 4) {
            val nx = x + dx[i]
            val ny = y + dy[i]
            if (nx !in graph.indices || ny !in graph[0].indices) continue
            if (graph[nx][ny] != type.ch) continue
            if (visited[nx][ny]) continue
            q.add(Pair(nx, ny))
            visited[nx][ny] = true
        }
    }
    answer[type.ordinal] += count * count
}
