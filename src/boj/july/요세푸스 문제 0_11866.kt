package boj.july

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val input = readLine().split(' ').map { it.toInt() }
    val queue: Queue<Int> = LinkedList()
    val answer = StringBuilder("<")
    // deque init
    repeat(input[0]) {
        queue.offer(it + 1)
    }
    // 순서대로 제거
    while (queue.isNotEmpty()) {
        // 제거할 위치를 앞으로
        repeat(input[1] - 1) {
            queue.offer(queue.poll())
        }
        // 제거되는 사람 추가
        if (queue.size == 1) {
            answer.append("${queue.poll()}>")
        } else {
            answer.append("${queue.poll()}, ")
        }
    }
    println(answer)
}
