package boj.august

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max

private val list = ArrayList<Pair<Int, Int>>()
fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val end = readLine().toInt()
    val dp = IntArray(end + 1)
    // 상담 정보
    for (i in 0 until end) {
        val input = readLine().split(' ').map { it.toInt() }
        list.add(Pair(input[0], input[1]))
    }
    // 최대로 얻을수 있는 돈 확인
    println(consult(dp, end))
}

private fun consult(dp: IntArray, end: Int): Int {
    for (day in 0 until end) {
        if (0 < day) {
            dp[day] = max(dp[day - 1], dp[day])
        }
        val nextConsultDay = day + list[day].first
        val cost = list[day].second
        // 삼담 날짜의 범위를 벗어나지 않았다면
        if (nextConsultDay in 1..end) {
            dp[nextConsultDay] = max(dp[nextConsultDay], dp[day] + cost)
        }
    }
    return max(dp[end], dp[end - 1])
}
