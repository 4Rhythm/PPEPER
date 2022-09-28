package boj.september

import java.io.BufferedReader
import java.io.InputStreamReader

private val dx = intArrayOf(-1, 0, 1, 0)
private val dy = intArrayOf(0, 1, 0, -1)
private lateinit var board: Array<IntArray>
private lateinit var visited: Array<BooleanArray>
var count = 0
fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val input = readLine().split(' ').map { it.toInt() }
    board = Array(input[0]) { IntArray(input[1]) }
    visited = Array(input[0]) { BooleanArray(input[1]) }
    val position = readLine().split(' ').map { it.toInt() }
    for (i in 0 until input[0]) {
        board[i] = readLine().split(' ').map { it.toInt() }.toIntArray()
    }
    cleanRoom(position[0], position[1], position[2])
    println(count)
}

private fun cleanRoom(x: Int, y: Int, direction: Int) {
    // 1. 현재 방 청소
    if (!visited[x][y]) {
        visited[x][y] = true
        count++
    }
    var dir = direction
    // 2. 모든 방향 탐색 -> 바라보는 방향 왼쪽부터
    repeat(4) {
        val cx = x + dx[(dir + 3) % 4]
        val cy = y + dy[(dir + 3) % 4]
        if (isValid(cx, cy)) {
            if (board[cx][cy] == 0 && !visited[cx][cy]) {
                cleanRoom(cx, cy, (dir + 3) % 4)
                return
            }
        }
        // 방향 유지
        dir = (dir + 3) % 4
    }
    // 4. 후진 방향 확인
    val bx = x + dx[(dir + 2) % 4]
    val by = y + dy[(dir + 2) % 4]
    if (isValid(bx,by)) {
        if (board[bx][by] == 1) {
            return
        }
        cleanRoom(bx, by, direction)
    }
}

private fun isValid(x: Int, y: Int): Boolean {
    return 0 <= x && x < board.size && 0 <= y && y < board[0].size
}
