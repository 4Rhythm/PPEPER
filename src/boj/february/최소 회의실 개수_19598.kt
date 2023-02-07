package boj.february

import java.util.PriorityQueue

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val pq = PriorityQueue<Pair<Long, Long>>(compareBy( { it.first }, { it.second }))
    repeat(n) {
        val (start, end) = readLine().split(" ").map { it.toLong() }
        pq.add(Pair(start, end))
    }
    val endTime = PriorityQueue<Long>()
    while (pq.isNotEmpty()) {
        val curr = pq.poll()
        if (endTime.isNotEmpty() && endTime.peek() <= curr.first) {
            endTime.poll()
        }
        endTime.offer(curr.second)
    }
    println(endTime.size)
}