package boj.march.Problem1912

import kotlin.math.max

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val sum = IntArray(n + 1)
    readLine().split(" ").map { it.toInt() }.forEachIndexed { index, i ->
        sum[index + 1] = max(sum[index] + i, i)
    }
    var max = Int.MIN_VALUE
    for (i in 1..n) {
        max = max.coerceAtLeast(sum[i])
    }
    println(max)
}