package boj.november

import java.util.PriorityQueue

fun main() {
    val test = readln().toInt()
    val answer = IntArray(test) { 0 }
    repeat(test) {
        val size = readln().toInt()
        val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.first }).apply {
            for (i in 0 until size) {
                val (score1, score2) = readLine()!!.split(' ').map { it.toInt() }
                add(Pair(score1, score2))
            }
        }
        var count = 1
        var currScore = pq.poll().second
        while (pq.isNotEmpty()) {
            val curr = pq.poll()
            // 등수가 더 높으면 뽑힐 수 있다
            if (curr.second < currScore) {
                currScore = curr.second
                count++
            }
        }
        answer[it] = count
    }
    answer.forEach {
        println(it)
    }
}