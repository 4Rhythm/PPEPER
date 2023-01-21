package boj.january

import kotlin.math.max

fun main() = with(System.`in`.bufferedReader()) {
    val N = readLine().toInt()
    val money = IntArray(N + 1)
    repeat(N) { day ->
        val ( T, P) = readLine().split(" ").map { it.toInt() }
        if (0 < day) money[day] = money[day].coerceAtLeast(money[day - 1])
        // 딱 일 할 수 있는 날짜
        if (day + T <= N) {
            money[day + T] = money[day + T].coerceAtLeast(money[day] + P)
        }
        // 마지막 날에 일을 할 수 있음
        if (day == N && T == 1) {
            money[day] += P
        }
    }
    // 마지막날이 1이면 일을 할 수 있다
    println(max(money[N], money[N - 1]))
}