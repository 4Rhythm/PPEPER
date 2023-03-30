package boj.march.Problem11053

import kotlin.math.max

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val list = readLine().split(" ").map { it.toInt() }
    val dp = IntArray(n)
    for (i in 0 until n) {
        dp[i] = 1
        for (j in 0 until i) {
            if (list[j] < list[i]) {
                dp[i] = max(dp[i], dp[j] + 1)
            }
        }
    }
    var answer = 0
    for (value in dp) {
        answer = answer.coerceAtLeast(value)
    }
    println(answer)
}