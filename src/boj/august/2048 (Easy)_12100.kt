package boj.august

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private var MAX = 0
private var SIZE = 0
fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val size = readLine().toInt()
    SIZE = size
    val board = Array(size) { IntArray(size) }
    for (i in 0 until size) {
        val input = readLine().split(' ').map { it.toInt() }
        board[i] = input.toIntArray()
    }
    makeNumber(board, 0)
    println(MAX)
}

private fun makeNumber(board: Array<IntArray>, move: Int) {
    if (move == 5) {
        MAX = MAX.coerceAtLeast(maxNumber(board))
        return
    }
    for (key in 0 until 4) {
        // 현재 보드 기억
        val curr = currentBoard(board)
        when (key) {
            // 좌 우
            0, 1 -> {
                for (i in 0 until SIZE) {
                    if (key == 0) {
                        curr[i] = merge(curr[i])
                    } else {
                        curr[i] = merge(curr[i].reversedArray()).reversedArray()
                    }
                }
            }
            // 상 하
            2, 3 -> {
                for (i in 0 until SIZE) {
                    // vertical방향에 대한 배열
                    val vertical = IntArray(SIZE)
                    for (j in 0 until SIZE) {
                        vertical[j] = curr[j][i]
                    }
                    if (key == 2) {
                        val list = merge(vertical)
                        for (j in 0 until SIZE) {
                            curr[j][i] = list[j]
                        }
                    } else {
                        val list = merge(vertical.reversedArray()).reversedArray()
                        for (j in SIZE - 1 downTo 0) {
                            curr[j][i] = list[j]
                        }
                    }
                }
            }
        }
        makeNumber(curr,move + 1)
        // current board로 다시 바꿔줌
    }
}

private fun merge(list: IntArray): IntArray {
    val filter = list.filter { it != 0 }
    val stack = Stack<Int>()
    val mergeList = IntArray(list.size) { 0 }
    var isMerge = false
    filter.forEach { number ->
        if (stack.isEmpty()) {
            stack.push(number)
        } else {
            val current = stack.peek()
            // 숫자가 같으면 합쳐진다
            if (current == number && !isMerge) {
                stack.pop()
                stack.push(number * 2)
                isMerge = true
            } else {
                stack.push(number)
                isMerge = false
            }
        }
    }
    // 리스트로 변환 -> 없는자리는 0
    for (i in stack.indices) {
        mergeList[i] = stack[i]
    }
    return mergeList
}

private fun maxNumber(board: Array<IntArray>): Int {
    var max = 0
    for (i in board.indices) {
        for (j in board.indices) {
            max = max.coerceAtLeast(board[i][j])
        }
    }
    return max
}

private fun currentBoard(board: Array<IntArray>): Array<IntArray> {
    val currentBoard = Array(board.size) { IntArray(board.size) }
    for (i in board.indices) {
        currentBoard[i] = board[i].clone()
    }
    return currentBoard
}
