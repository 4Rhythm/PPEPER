package boj.january

import java.lang.StringBuilder

private val answer = StringBuilder()
private lateinit var visited: BooleanArray
private lateinit var list: List<Int>
private var M = 0
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    list = readLine().split(" ").map { it.toInt() }.sorted()
    M = m
    visited = BooleanArray(list.size + 1)
    backtracking5(0, "")
    println(answer)
}

private fun backtracking5(count: Int, result: String) {
    if (count == M) {
        answer.append(result).append("\n")
        return
    }
    for (i in list.indices) {
        if (!visited[i]) {
            visited[i] = true
            backtracking5(count + 1, "$result${list[i]} ")
            visited[i] = false
        }
    }
}
