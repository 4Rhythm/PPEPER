package src.boj.october.Problem1021

import java.util.ArrayDeque

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val targets = readLine().split(" ").map { it.toInt() }
    val deque = ArrayDeque<Int>()
    repeat(n) { deque.offer(it + 1) }
    var count = 0
    for (target in targets) {
        while (true) {
            if (target == deque.peek()) {
                deque.removeFirst()
                break
            }
            if (deque.indexOf(target) <= deque.size / 2) {
                while (deque.peek() != target) {
                    deque.pollFirst().also { deque.offerLast(it) }
                    count += 1
                }
            } else {
                while (deque.peek() != target) {
                    deque.pollLast().also { deque.offerFirst(it) }
                    count += 1
                }
            }
        }
    }
    println(count)
}