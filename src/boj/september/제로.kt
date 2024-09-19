package src.boj.september

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val k = readLine().toInt()
    val stack = Stack<Int>()
    repeat(k) {
        val input = readLine().toInt()
        if (input == 0) stack.pop()
        else stack.push(input)
    }
    println(stack.sum())
}
