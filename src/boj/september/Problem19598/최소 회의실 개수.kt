package src.boj.september.Problem19598

import java.util.PriorityQueue

private lateinit var list: List<List<Long>>
private lateinit var endTime: PriorityQueue<Long>
fun main() = with(System.`in`.bufferedReader()) {
    list = List(readLine().toInt()) { readLine().split(" ").map { it.toLong() } }
        .sortedBy { it[0] }
    endTime = PriorityQueue()
    // 시작시간에 맞춰 시작은 해야함
    // 가장 끝나는 시간이 빠른 회의실과 비교를 통해 사용할 수 있다면 사용 or 새로운 회의실 개설
    for ((start, end) in list) {
        if (endTime.isNotEmpty() && endTime.peek() <= start) {
            endTime.poll()
        }
        endTime.add(end)
    }
    println(endTime.size)
}