package boj.august

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.abs

private var COUNT = 0
fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val N = readLine().toInt()
    val chess = IntArray(N)
    // 체스판에 돌 두기
    putChess(0, chess, N)
    println(COUNT)
}

private fun putChess(row: Int, chess: IntArray, N: Int) {
    // N x N -> 행이 N개면 종료
    if (row == N) {
        COUNT++
        return
    }
    for (pos in 1..N) {
        // row에 index위치에 둘 수 있나 확인
        if (isValid(row, pos, chess)) {
            chess[row] = pos
            putChess(row + 1, chess, N)
        }
    }

}

private fun isValid(row: Int, pos: Int, chess: IntArray): Boolean {
    // 두려는 row행 전까지 확인
    // row에 index를 둘 수 있나 확인
    for (i in 0 until row) {
        // 같은 행, 대각선은 안됨
        if (chess[i] == pos || row - i == abs(chess[i] - pos)) {
            return false
        }
    }
    return true
}
