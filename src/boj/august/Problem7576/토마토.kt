package src.boj.august.Problem7576

import java.util.*

private val dx = intArrayOf(1, -1, 0, 0)
private val dy = intArrayOf(0, 0, 1, -1)
private var count = 0
private var day = 0
private lateinit var tomato: Array<IntArray>
private lateinit var queue: LinkedList<Pair<Int, Int>>
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    tomato = Array(m) { IntArray(n) }
    queue = LinkedList()
    for (i in 0 until m) {
        val input = readLine().split(" ").map { it.toInt() }
        for (j in 0 until n) {
            tomato[i][j] = input[j]
            if (input[j] == 0) count++
            else if (input[j] == 1) queue.add(Pair(i, j))
        }
    }
    bfs()
    if (count == 0) println(day) else println(-1)

}

private fun bfs() {
    while (0 < count && queue.isNotEmpty()) {
        repeat(queue.size) {
            val (x, y) = queue.poll()
            for (i in 0 until 4) {
                val nx = x + dx[i]
                val ny = y + dy[i]
                if (nx !in tomato.indices || ny !in tomato[0].indices) continue
                if (tomato[nx][ny] == 0) {
                    tomato[nx][ny] = 1
                    count--
                    queue.add(Pair(nx, ny))
                }
            }
        }
        day++
    }
}
