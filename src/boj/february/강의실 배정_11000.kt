package boj.february

import java.util.PriorityQueue

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val pq = PriorityQueue<Pair<Int, Int>>(compareBy( { it.first }, { it.second }))
    val endTime = PriorityQueue<Int>()
    repeat(n) {
        val input = readLine().split(" ").map { it.toInt() }
        pq.add(Pair(input[0], input[1]))
    }
    // 첫 강의실
    // 모든 강의를 들을 수 있어야함
    while (pq.isNotEmpty()) {
        val curr = pq.poll()
        // 끝나는 시간보다 강의가 늦게 시작해야함
        if (endTime.isNotEmpty() && endTime.peek() <= curr.first) {
            endTime.poll()
        }
        endTime.offer(curr.second)
    }
    println(endTime.size)
}