package boj.july

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private var dx = intArrayOf(0, 1, 0, -1)    // 우하좌상
private var dy = intArrayOf(1, 0, -1, 0)
private lateinit var BOARD: Array<IntArray>
private var DIRECTION = 0
private var TIME = 0
fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val size = readLine().toInt()
    val action: Queue<Pair<Int, String>> = LinkedList()
    BOARD = Array(size) { IntArray(size) { 0 } }
    // 사과 개수
    val number = readLine().toInt()
    // 사과 위치 init
    for (i in 0 until number) {
        val pos = readLine().split(' ').map { it.toInt() }
        BOARD[pos[0] - 1][pos[1] - 1] = 10
    }
    // 이동 경로
    val move = readLine().toInt()
    val snake: Deque<Pair<Int, Int>> = LinkedList()
    // 처음 시작
    snake.offer(Pair(0, 0))
    BOARD[0][0] = 1
    // 뱀의 이동시간과 회전 저장
    for (j in 0 until move) {
        val input = readLine().split(' ')
        action.offer(Pair(input[0].toInt(), input[1]))
    }
    // 뱀의 종료 시간 구하기
    while (true) {
        TIME++
        val x = snake.last.first + dx[DIRECTION % 4]
        val y = snake.last.second + dy[DIRECTION % 4]
        // 벽에 부딪치거나 자기자신에게 부딪치면 종료
        if (isValid(x, y) && BOARD[x][y] != 1) {
            // 사과를 먹음
            if (BOARD[x][y] == 10) {
                snake.offer(Pair(x, y))
                BOARD[x][y] = 1
            } else {
                val poll = snake.poll()
                // 초기화
                BOARD[poll.first][poll.second] = 0
                snake.offer(Pair(x, y))
                BOARD[x][y] = 1
            }
        } else {
            break
        }

        // 잘 이동했으면 방향 전환 확인
        if (action.isNotEmpty()) {
            // 이동시간이 되었다면 -> 회전
            if (action.peek().first == TIME) {
                DIRECTION += if (action.poll().second == "D") {
                    1
                } else {
                    3
                }
            }
        }
    }
    println(TIME )
}

private fun isValid(x: Int, y: Int): Boolean = x >= 0 && x < BOARD.size && y >= 0 && y < BOARD.size