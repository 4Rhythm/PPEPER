package boj.september

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayList

private var turn = 0
private lateinit var board: Array<IntArray>
private lateinit var chess: ArrayList<Chess>
private lateinit var stack: Array<Array<Stack<Int>>>

// 1, 2, 3, 4 -> 오른쪽, 왼쪽, 위, 아래
private val DX = intArrayOf(0, 0, -1, 1)
private val DY = intArrayOf(1, -1, 0, 0)

data class Chess(
    var x: Int,
    var y: Int,
    var direction: Int
)

fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val input = readLine().split(' ').map { it.toInt() }
    board = Array(input[0]) { IntArray(input[0]) }
    chess = ArrayList()
    stack = Array(input[0]) { Array(input[0]) { Stack() } }
    // board init
    for (i in 0 until input[0]) {
        board[i] = readLine().split(' ').map { it.toInt() }.toIntArray()
    }
    // 체스들 정보 init
    for (i in 0 until input[1]) {
        val input = readLine().split(' ').map { it.toInt() }
        chess.add(Chess(input[0] - 1, input[1] - 1, input[2] - 1))
        // 해당 위치에 체스의 순서에 해당하는 값을 둔다.
        stack[input[0] - 1][input[1] - 1].push(i)
    }
    playChess()
    if (1000 < turn) {
        println(-1)
    } else {
        // 턴 종료의 한번의 턴을 안 더해줬으므로 + 1
        println(turn + 1)
    }
}

private fun playChess() {
    while (true) {
        var dx: Int
        var dy: Int
        // 한번 턴엔 모든 체스말을 순서대로 이동
        for (index in chess.indices) {
            val c = chess[index]
            val x = c.x
            val y = c.y
            dx = x + DX[c.direction]
            dy = y + DY[c.direction]
            // 현재 말 전의 말들
            val count = checkRange(index, stack[x][y])
            val s = stack[x][y]

            // 범위를 벗어나거나 파란색 칸이라면
            if (!isValid(dx, dy) || board[dx][dy] == 2) {
                changeDirection(chess[index])
                // 바꾼 방향으로 이동 확인
                dx = x + DX[c.direction]
                dy = y + DY[c.direction]

                // 바꾼 이동 경로도 불가능 -> 이동 끝
                if (!isValid(dx, dy) || board[dx][dy] == 2) {
                    continue
                }
            }
            // 말 전체 이동
            moveAll(count, dx, dy, s, board[dx][dy])
            // 말의 개수가 4개 이상이면 종료
            if (4 <= stack[dx][dy].size || 1000 < turn) {
                return
            }
        }
        // 턴한번 지남
        turn++
    }
}

private fun checkRange(index: Int, stack: Stack<Int>): Int {
    for (i in stack.indices) {
        if (stack[i] == index) {
            return i
        }
    }
    return -1
}

private fun moveAll(count: Int, dx: Int, dy: Int, s: Stack<Int>, i: Int) {
    // count 전까지만 이동
    while (s.size > count) {
        // 빨간색 pop
        val order: Int = if (i == 1) {
            s.pop()
        } else {
            // 빤간색
            s.removeAt(count)
        }
        // 체스의 x, y를 바꿔줌
        chess[order].x = dx
        chess[order].y = dy
        // 새로운 위치에 넣어줌
        stack[dx][dy].push(order)
    }
}

private fun changeDirection(chess: Chess) {
    when (chess.direction) {
        0 -> {
            chess.direction = 1
        }
        1 -> {
            chess.direction = 0
        }
        2 -> {
            chess.direction = 3
        }
        3 -> {
            chess.direction = 2
        }
    }
}

private fun isValid(x: Int, y: Int): Boolean {
    return 0 <= x && x < board.size && 0 <= y && y < board.size
}
