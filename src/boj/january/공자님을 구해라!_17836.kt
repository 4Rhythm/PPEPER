package boj.january

import java.util.*
import kotlin.math.min

data class Knight(var x: Int, var y: Int, var time: Int)
private val dx = intArrayOf(1, -1, 0, 0)
private val dy = intArrayOf(0, 0, 1, -1)
private lateinit var board: Array<IntArray>
private lateinit var distance: Array<IntArray>
private lateinit var visited: Array<BooleanArray>
fun main() = with(System.`in`.bufferedReader()) {
    val (N, M, T) = readLine().split(" ").map { it.toInt() }
    var swordX = 0
    var swordY = 0
    board = Array(N) { IntArray(M) }
    repeat(N) { i ->
        val input = readLine().split(" ").map { it.toInt() }
        board[i] = input.toIntArray()
        for (j in input.indices) {
            if (input[j] == 2) {
                swordX = i
                swordY = j
            }
        }
    }
    distance = Array(N) { IntArray(M) }
    visited = Array(N) { BooleanArray(M) }
    rescuePrincess()
    // 검을 얻지 않고 이동
    val time1 = if (distance[N - 1][M - 1] != 0) distance[N - 1][M - 1] else Int.MAX_VALUE
    // 검을 얻고 이동 -> 검까지의 거리 + 공주까지 맨허튼 거리
    val time2 = if (distance[swordX][swordY] != 0) distance[swordX][swordY] + N - swordX - 1 + M - swordY - 1 else Int.MAX_VALUE
    if (T < min(time1, time2)) println("Fail") else println(min(time1, time2))
}

private fun rescuePrincess() {
    val k = Knight(0, 0, 0)
    val queue = LinkedList<Knight>()
    queue.offer(k)
    visited[0][0] = true
    while (queue.isNotEmpty()) {
        val curr = queue.poll()
        visited[curr.x][curr.y] = true
        for (i in dx.indices) {
            val cx = curr.x + dx[i]
            val cy = curr.y + dy[i]
            if (isValid(cx, cy)) {
                if (!visited[cx][cy] && board[cx][cy] != 1) {
                    queue.offer(Knight(cx, cy, curr.time + 1))
                    visited[cx][cy] = true
                    distance[cx][cy] = curr.time + 1
                }
            }
        }
    }
}

private fun isValid(x: Int, y: Int): Boolean {
    return x in board.indices && y in board[0].indices
}
