package boj.April.Problem2156

import kotlin.math.max

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val list = IntArray(n + 1)
    repeat(n) {
        list[it + 1] = readLine().toInt()
    }
    val dp = IntArray(n + 1)
    for (i in 1..n) {
        when (i) {
            1 -> dp[i] = list[i]
            2 -> dp[i] = list[i - 1] + list[i]
            else -> {
                dp[i] = dp[i - 1].coerceAtLeast(dp[i - 3] + list[i - 1] + list[i])
                    .coerceAtLeast(dp[i - 2] + list[i])
            }
        }
    }
    println(dp[n])
}