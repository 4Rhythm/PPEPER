//package boj.july
//
//import java.io.BufferedReader
//import java.io.InputStreamReader
//
//val dx = intArrayOf(-1, 0, 1, 0) // 좌우
//val dy = intArrayOf(0, 1, 0, -1) // 상하
//lateinit var board: Array<IntArray>
//lateinit var visited: Array<BooleanArray>
//private var max = 0
//fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
//    val input = readLine().split(' ').map { it.toInt() }
//    board = Array(input[0]) { IntArray(input[1]) }
//    visited = Array(board.size) { BooleanArray(board[0].size) }
//    // 종이에 쓰여 있는 수
//    for (i in 0 until input[0]) {
//        val number = readLine().split(' ').map { it.toInt() }
//        // 보드에 숫자 init
//        for (j in number.indices) {
//            board[i][j] = number[j]
//        }
//    }
//    // 합의 최댓값 구하기
//    for (i in board.indices) {
//        for (j in board[i].indices) {
//            classification(intArrayOf(i, j), board[i][j], 1)
//            checkPurple(intArrayOf(i, j), board[i][j])
//        }
//    }
//    println(max)
//}
//
//private fun checkPurple(pos: IntArray, value: Int) {
//    var check = 4
//    var sum = value
//    for (i in 0 until 4) {
//        val x = pos[0] + dx[i]
//        val y = pos[1] + dy[i]
//        if (check < 3) {
//            return
//        }
//        if (!isValid(x, y)) {
//            check--
//        } else {
//            sum += board[x][y]
//        }
//    }
//    // 모든 경우가 가능하다면 하나씩 확인
//    if (check == 4) {
//        for (i in 0 until 4) {
//            val x = pos[0] + dx[i]
//            val y = pos[1] + dy[i]
//            max = max.coerceAtLeast(sum - board[x][y])
//        }
//    } else {
//        max = max.coerceAtLeast(sum)
//    }
//}
//private fun classification(pos: IntArray, value: Int, depth: Int){
//    if (depth == 4) {
//        max = max.coerceAtLeast(value)
//        return
//    } else {
//        visited[pos[0]][pos[1]] = true
//        for (i in 0 until 4) {
//            val x = pos[0] + dx[i]
//            val y = pos[1] + dy[i]
//            if (isValid(x, y) && !visited[x][y]) {
//                visited[x][y] = true
//                classification(intArrayOf(x, y), value + board[x][y], depth + 1)
//                visited[x][y] = false
//            }
//        }
//    }
//    visited[pos[0]][pos[1]] = false
//}
//
//private fun isValid(x: Int, y: Int): Boolean {
//    return (x >= 0 && x < board.size && y >= 0 && y < board[0].size)
//}
