package boj.october

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private val dx = intArrayOf(-1, 1, 0, 0)
private val dy = intArrayOf(0, 0, -1, 1)
private lateinit var board: Array<IntArray>
private var max = 0
fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (N, M) = readLine().split(' ').map { it.toInt() }
    board = Array(N) { IntArray(M) }
    for (i in 0 until N) {
        val input = readLine().split(' ').map { it.toInt() }
        board[i] = input.toIntArray()
    }
    // 바둑알 2개 두기
    putWhiteBaduk(0)
    println(max)
}

private fun putWhiteBaduk(count: Int) {
    if (count == 2) {
        var black = 0
        val visited = Array(board.size) { BooleanArray(board[0].size) }
        for (i in board.indices) {
            for (j in board[0].indices) {
                // 검은돌
                if (board[i][j] == 2 && !visited[i][j]) {
                    black += checkGame(i, j, visited)
                }
            }
        }
        // 최대로 먹을 수 있는 돌 비교
        max = max.coerceAtLeast(black)
        return
    }
    for (i in board.indices) {
        for (j in board[0].indices) {
            if (board[i][j] == 0) {
                board[i][j] = 1
                putWhiteBaduk(count + 1)
                board[i][j] = 0
            }
        }
    }
}

private fun checkGame(i: Int, j: Int, visited: Array<BooleanArray>): Int {
    var black = 1
    var check = true
    val q: Queue<Pair<Int, Int>> = LinkedList()
    q.add(Pair(i, j))
    visited[i][j] = true
    while (q.isNotEmpty()) {
        val curr = q.poll()
        for (k in 0 until 4) {
            val nx = curr.first + dx[k]
            val ny = curr.second + dy[k]
            if (isValid(nx, ny)) {
                // 빈칸이면 흰돌이 먹을 수 있다
                if (board[nx][ny] == 2 && !visited[nx][ny]) {
                    black++
                    visited[nx][ny] = true
                    q.offer(Pair(nx, ny))
                }
                if (board[nx][ny] == 0) {
                    // 열려있는 곳이 있으면 돌을 먹을 수 없다
                    check = false
                }
            }
        }
    }
    return if (check) black else 0
}

private fun isValid(x: Int, y: Int): Boolean {
    return x in board.indices && y in board[0].indices
}
