package boj.october

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayList

private lateinit var board: Array<IntArray>
private val virus = ArrayList<Pair<Int, Int>>()
private val dx = intArrayOf(1, -1, 0, 0)
private val dy = intArrayOf(0, 0, 1, -1)
private var N = 0
private var M = 0
private var max = 0
fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (n, m) = readLine().split(' ').map { it.toInt() }
    N = n
    M = m
    board = Array(N) { IntArray(M) }
    for (i in 0 until N) {
        val value = readLine().split(' ').map { it.toInt() }
        board[i] = value.toIntArray()
        for (j in value.indices) {
            if (value[j] == 2) {
                virus.add(Pair(i,j))
            }
        }
    }
    // 벽 3개를 모두 세워본다
    dfs(0)
    println(max)
}

private fun dfs(count: Int) {
    if (count == 3) {
        val copyBoard = Array(N) { IntArray(M) }
        for (i in board.indices) {
            copyBoard[i] = board[i].copyOf()
        }
        // 최대 안전지대로 업데이트
        for (v in virus) {
            virusZone(v, copyBoard)
        }
        safeZone(copyBoard)
        return
    }
    for (i in board.indices) {
        for (j in board[i].indices) {
            if (board[i][j] == 0) {
                board[i][j] = 1
                dfs(count + 1)
                board[i][j] = 0
            }
        }
    }

}

private fun safeZone(copyBoard: Array<IntArray>) {
    var count = 0
    for (i in copyBoard.indices) {
        for (j in copyBoard[i].indices) {
            if (copyBoard[i][j] == 0) {
                count++
            }
        }
    }
    max = max.coerceAtLeast(count)
}

private fun virusZone(virus: Pair<Int, Int>, copyBoard: Array<IntArray>){
    val q = LinkedList<Pair<Int, Int>>()
    q.offer(virus)
    while (q.isNotEmpty()) {
        val v = q.poll()
        for (i in 0 until 4) {
            val cx = v.first + dx[i]
            val cy = v.second + dy[i]
            if (isValid(cx, cy)) {
                if (copyBoard[cx][cy] == 0) {
                    copyBoard[cx][cy] = 2
                    q.offer(Pair(cx, cy))
                }
            }
        }
    }
}

private fun isValid(x: Int, y: Int): Boolean {
    return 0 <= x && x < board.size && 0 <= y && y < board[0].size
}