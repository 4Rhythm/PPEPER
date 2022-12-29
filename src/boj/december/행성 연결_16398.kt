package boj.december

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private lateinit var planet: Array<LinkedList<Pair<Int, Int>>>
private lateinit var visited: BooleanArray
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var cost = 0L
    val N = readLine().toInt()
    planet = Array(N + 1) { LinkedList() }
    visited = BooleanArray(N + 1)
    for (i in 0 until N) {
        val input = readLine().split(' ').map { it.toInt() }
        for (j in input.indices) {
            if (i != j) {
                planet[i + 1].add(Pair(j + 1, input[j]))
            }
        }
    }
    val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
    pq.offer(Pair(1, 0))
    while (pq.isNotEmpty()) {
        val curr = pq.poll()
        if (!visited[curr.first]) {
            visited[curr.first] = true
            cost += curr.second
            for (nextNode in planet[curr.first]) {
                if (!visited[nextNode.first]) {
                    pq.offer(nextNode)
                }
            }
        }
    }
    println(cost)
}