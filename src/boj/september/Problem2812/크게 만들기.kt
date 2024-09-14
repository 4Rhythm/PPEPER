package src.boj.september.Problem2812

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val (n, k) = readLine().split(" ").map { it.toInt() }
    val number = readLine()
    val pick = Stack<Char>()
    var count = 0
    for (i in number.indices) {
        if (count == k) {
            for (c in number.substring(i)) pick.push(c)
            break
        }
        while (pick.isNotEmpty() && pick.peek() < number[i] && count < k) {
            pick.pop()
            count++
        }
        pick.push(number[i])
    }
    while (count < k) {
        pick.pop()
        count++
    }
    println(pick.joinToString("".reversed()))
}