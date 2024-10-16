package src.boj.october.Problem2470

import kotlin.math.abs

private lateinit var list: List<Int>
private var diffCloseZero = Int.MAX_VALUE
private lateinit var answer: IntArray
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    list = readLine().split(" ").map { it.toInt() }.sorted()
    answer = IntArray(2)
    var left = 0
    var right = n - 1

    while (left != right) {
        val diff = list[right] + list[left]
        if (abs(diff) < diffCloseZero) {
            diffCloseZero = abs(diff)
            answer[0] = list[left]
            answer[1] = list[right]
        }
        if (diff < 0) {
            left += 1
        } else {
            right -= 1
        }
    }
    println(answer.joinToString(" "))
}