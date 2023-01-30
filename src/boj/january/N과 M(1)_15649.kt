package boj.january

import java.lang.StringBuilder

private val answer = StringBuilder()
private lateinit var visited: BooleanArray
fun main() = with(System.`in`.bufferedReader()) {
    val (N, M) = readLine().split(" ").map { it.toInt() }
    visited = BooleanArray(N + 1)
    backtracking1(N, M, "")
    println(answer)
}

private fun backtracking1(n: Int, m: Int, result: String) {
    if (m == 0) {
        answer.append(result).append("\n")
        return
    }
    for (i in 1..n) {
        if (!visited[i]) {
            visited[i] = true
            backtracking1(n, m - 1, "$result$i ")
            visited[i]= false
        }
    }
}
