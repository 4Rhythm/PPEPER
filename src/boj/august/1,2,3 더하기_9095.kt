package boj.august

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val case = readLine().toInt()
    val numberList = IntArray(case)
    var max = 0
    for (i in 0 until case) {
        numberList[i] = readLine().toInt()
        max = max.coerceAtLeast(numberList[i])
    }
    // 만들수 있는 가짓수
    val dp = IntArray(max + 1)
    dp[1] = 1
    dp[2] = 2
    dp[3] = 4
    for (i in 4 until dp.size) {
        dp[i] = dp[i-3] + dp[i-2] + dp[i-1]
    }
    numberList.forEach {
        println(dp[it])
    }
}