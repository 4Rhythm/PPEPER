package boj.august

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max

fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val size = readLine().toInt()
    val triangle = Array(size) { IntArray(size) }
    // 삼각형 만들기
    for (i in 0 until size) {
        triangle[i] = readLine().split(' ').map { it.toInt() }.toIntArray()
    }
    // 아래에서 부터 구하기
    for (i in size - 1 downTo 1) {
        for (j in 0 until i) {
            triangle[i-1][j] = triangle[i-1][j] + max(triangle[i][j], triangle[i][j + 1])
        }
    }
    println(triangle[0][0])
}
