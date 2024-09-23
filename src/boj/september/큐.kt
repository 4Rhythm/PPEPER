package src.boj.september

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val deque = ArrayDeque<Int>()
    repeat(n) {
        val input = readLine().split(" ")
        when (input[0]) {
            "push" -> {
                deque.addFirst(input[1].toInt())
            }
            "front" -> {
                println(deque.peekLast() ?: -1)
            }
            "back" -> {
                println(deque.peekFirst() ?: -1)
            }
            "size" -> {
                println(deque.size)
            }
            "empty" -> {
                if (deque.isEmpty()) println(1) else println(0)
            }
            "pop" -> {
                if (deque.isEmpty()) println(-1) else println(deque.removeLast())
            }
        }
    }
}