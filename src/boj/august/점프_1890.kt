package boj.august

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val size = readLine().toInt()
    val answer = Array(size) { LongArray(size) { 0 } }
    val board = Array(size) { IntArray(size) }
    for (i in 0 until size) {
        board[i] = readLine().split(' ').map { it.toInt() }.toIntArray()
    }
    // 시작점
    answer[0][0] = 1
    // 경로 탐색
    for (x in 0 until size) {
        for (y in 0 until size) {
            if (answer[x][y] >= 1 && board[x][y] != 0) {
                val move = board[x][y]
                if (x + move < size) {
                    answer[x + move][y] += answer[x][y]
                }
                if (y + move < size) {
                    answer[x][y + move] += answer[x][y]
                }
            }
        }
    }
    println(answer[size-1][size-1])
}