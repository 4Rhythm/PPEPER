package src.boj.august.Problem2178

import java.util.*

private val dx = intArrayOf(1, -1, 0, 0)
private val dy = intArrayOf(0, 0, 1, -1)
private lateinit var graph: Array<CharArray>
private lateinit var visited: Array<IntArray>
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    graph = Array(n) { readLine().toCharArray() }
    visited = Array(n) { IntArray(m) }
    println(bfs())
}

private fun bfs(): Int {
    val q = LinkedList<Pair<Int, Int>>()
    q.add(Pair(0, 0))
    visited[0][0] = 1
    while (q.isNotEmpty()) {
        val (x, y) = q.poll()
        if (x == graph.size - 1 && y == graph[0].size - 1) {
            return visited[x][y]
        }
        for (i in 0 until 4) {
            val nx = x + dx[i]
            val ny = y + dy[i]
            if (nx !in graph.indices || ny !in graph[0].indices) continue
            if (graph[nx][ny] == '0') continue
            if (visited[nx][ny] != 0) continue
            visited[nx][ny] = visited[x][y] + 1
            q.add(Pair(nx, ny))
        }
    }
    return -1
}
