package boj.january

import java.lang.StringBuilder

private val answer = StringBuilder()
private lateinit var visited: BooleanArray
private var N = 0
private var M = 0
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    N = n
    M = m
    visited = BooleanArray(N + 1)
    backtracking4(1, 0,"")
    println(answer)
}

private fun backtracking4(start: Int, count: Int, result: String) {
    if (count == M) {
        answer.append(result).append("\n")
        return
    }
    for (i in start..N) {
        backtracking4(i, count + 1, "$result$i ")
    }
}
