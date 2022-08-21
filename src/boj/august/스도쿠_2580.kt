package boj.august

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.system.exitProcess

data class Node(
    var x: Int,
    var y: Int
)
private lateinit var board: Array<IntArray>
fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    board = Array(9) { IntArray(9) }
    val blank = ArrayList<Node>()
    for (i in 0 until 9) {
        val input = readLine().split(' ').map { it.toInt() }
        board[i] = input.toIntArray()
        // 빈칸 위치 저장
        for (j in input.indices) {
            if (input[j] == 0) {
                blank.add(Node(i, j))
            }
        }
    }
    sudoku(0, blank)
}

private fun sudoku(index: Int, blank: ArrayList<Node>) {
    if (index == blank.size) {
        for (i in board.indices) {
            for (j in board.indices) {
                print("${board[i][j]} ")
            }
            println()
        }
        exitProcess(0)
    }
    val node = blank[index]
    for (number in 1..9) {
        // 빈칸의 위치에 number가 들어 갈 수 있는지 확인
        if (isValid(node.x, node.y, number)) {
            board[node.x][node.y] = number
            sudoku(index + 1, blank)
            // 다시 초기화
            board[node.x][node.y] = 0
        }
    }
}

// 스도쿠 룰 확인
private fun isValid(x: Int, y: Int, number: Int): Boolean {
    // 가로, 세로
    for (i in 0 until 9) {
        if (board[x][i] == number || board[i][y] == number) {
            return false
        }
    }
    // 3 * 3 룰 확인
    val dx = x / 3 * 3
    val dy = y / 3 * 3
    for (i in dx until dx + 3) {
        for (j in dy until dy + 3) {
            if (board[i][j] == number) {
                return false
            }
        }
    }
    // 모두 만족
    return true
}