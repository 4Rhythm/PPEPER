package boj.june

import java.io.BufferedReader
import java.io.InputStreamReader

val COL = arrayOf('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H')
val ROW = arrayOf('8', '7', '6', '5', '4', '3', '2', '1')
val MOVE = arrayOf(
    intArrayOf(0,1),
    intArrayOf(0,-1),
    intArrayOf(1,0),
    intArrayOf(-1,0),
    intArrayOf(-1,1),
    intArrayOf(-1,-1),
    intArrayOf(1,1),
    intArrayOf(1,-1))
fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val input = readLine()!!.split(' ')
    val move = input[2].toInt()
    val king = intArrayOf(ROW.indexOf(input[0][1]), COL.indexOf(input[0][0]))
    val rock = intArrayOf(ROW.indexOf(input[1][1]), COL.indexOf(input[1][0]))
    // 돌의 이동
    for (i in 0 until move) {
        // 이동해야할 x,y값
        val move = MOVE[state(readLine())]
        val kingX = king[0] + move[0]
        val kingY = king[1] + move[1]
        // 게임 판을 벗어나면 안됨
        if (kingX < 0 || kingX > 7 || kingY < 0 || kingY > 7) {
            continue
        } else {
            // 킹이 이동한 위치에 돌이 있다면 -> 돌을 밀 수 있나 확인
            if (rock[0] == kingX && rock[1] == kingY) {
                val rockX = rock[0] + move[0]
                val rockY = rock[1] + move[1]
                if (rockX < 0 || rockX > 7 || rockY < 0 || rockY > 7) {
                    continue
                } else {
                    rock[0] = rockX
                    rock[1] = rockY
                }
            }
            king[0] = kingX
            king[1] = kingY
        }
    }
    println("${COL[king[1]]}${ROW[king[0]]}")
    println("${COL[rock[1]]}${ROW[rock[0]]}")
}
private fun state(str: String): Int {
    return when (str) {
        "R" -> 0
        "L" -> 1
        "B" -> 2
        "T" -> 3
        "RT" -> 4
        "LT" -> 5
        "RB" -> 6
        "LB" -> 7
        else -> -1
    }
}