package boj.march.Problem1890

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val list = Array(n) {
        readLine().split(" ").map { it.toInt() }
    }
    val dp = Array(n) { LongArray(n) }
    dp[0][0] = 1
    for (i in 0 until n) {
        for (j in 0 until n) {
            if (dp[i][j] != 0L && list[i][j] != 0) {
                val jump = list[i][j]
                if (i + jump < n) dp[i + jump][j] += dp[i][j]
                if (j + jump < n) dp[i][j + jump] += dp[i][j]
            }
        }
    }
    println(dp[n - 1][n - 1])
}