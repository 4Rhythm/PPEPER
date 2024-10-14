package src.boj.october.Problem1459

import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
    val (x, y, w, s) = readLine().split(" ").map { it.toLong() }
    // 가로 세로로만 가는 시간
    val time1 = (x + y) * w
    // 대각선 + 가로 세로 이동
    val time2 = min(x, y) * s + abs(x - y) * w
    val time3 = if ((x + y) % 2 == 0L) max(x, y) * s else (max(x, y) - 1) * s + w
    println(time1.coerceAtMost(time2).coerceAtMost(time3))
}