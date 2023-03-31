package boj.march.Problem11055

import kotlin.math.max

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val list = readLine().split(" ").map { it.toInt() }
    val dp = IntArray(n)
    for (i in list.indices) {
        dp[i] = list[i]
        // 0 ~ 현재까지 중 작은 친구 확인
        for (j in 0 until i) {
            if (list[j] < list[i]) {
                dp[i] = max(dp[j] + list[i], dp[i])
            }
        }
    }
    var max = 0
    for (value in dp) {
        max = max.coerceAtLeast(value)
    }
    println(max)
}