package boj.january

import java.lang.StringBuilder

private val answer = StringBuilder()
private lateinit var list: List<Int>
private var M = 0
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    list = readLine().split(" ").map { it.toInt() }.sorted()
    M = m
    backtracking7(0, "")
    println(answer)
}

private fun backtracking7(count: Int, result: String) {
    if (count == M) {
        answer.append(result).append("\n")
        return
    }
    for (element in list) {
        backtracking7(count + 1, "$result${element} ")
    }
}
