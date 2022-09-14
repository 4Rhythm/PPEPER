package boj.september

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val input = readLine().split(' ').map { it.toInt() }
    val list = IntArray(input[0])
    val dp = IntArray(input[1] + 1)
    for (i in 0 until input[0]) {
        list[i] = readLine().toInt()
    }
    dp[0] = 1
    for (coin in list) {
        // 동전의 개수로 표현
        for (i in 0..input[1]) {
            if (i >= coin) {
                dp[i] += dp[i - coin]
            }
        }
    }
    println(dp[dp.lastIndex])
}
