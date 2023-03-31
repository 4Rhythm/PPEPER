package boj.march.Problem9465

import java.lang.StringBuilder
import kotlin.math.max

val sb = StringBuilder()
fun main() = with(System.`in`.bufferedReader()) {
    val t = readLine().toInt()
    repeat(t) {
        val n = readLine().toInt()
        val list = Array(2) { readLine().split(" ").map { it.toInt() }}
        val dp = Array(2) { IntArray(n) }
        // 스티커 선택
        for (i in 0 until n) {
            dp[0][i] = list[0][i]
            dp[1][i] = list[1][i]
            // 전에와 비교는 대각선으로만 선택 가능
            if (0 < i) {
                dp[0][i] += dp[1][i - 1]
                dp[1][i] += dp[0][i - 1]
            }
            // 두번째 전에는 어디든 상관없음
            if (1 < i) {
                val temp = max(dp[0][i - 2], dp[1][i - 2])
                dp[0][i] = max(temp + list[0][i], dp[0][i])
                dp[1][i] = max(temp + list[1][i], dp[1][i])
            }
        }
        sb.append(max(dp[0][n - 1], dp[1][n - 1])).append("\n")
    }
    println(sb)
}