package boj.january

import kotlin.math.pow
import kotlin.math.sqrt

fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    val dp = IntArray(N + 1) { Int.MAX_VALUE }
    dp[0] = 0
    dp[1] = 1
    for (number in 2..N) {
        for (k in 1..sqrt(number.toFloat()).toInt()) {
            dp[number] = dp[number].coerceAtMost(dp[number - k.toFloat().pow(2).toInt()] + 1)
        }
    }
    println(dp[N])
}
