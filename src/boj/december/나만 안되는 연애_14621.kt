package boj.december

import java.util.*

data class School(val end: Int, val weight: Int, val sex: String)
private lateinit var graph: Array<LinkedList<School>>
private lateinit var visited: BooleanArray
fun main() = with(System.`in`.bufferedReader()) {
    var length = 0
    val (N, M) = readLine().split(" ").map { it.toInt() }
    val sexList = readLine().split(" ")
    graph = Array(N + 1) { LinkedList() }
    visited = BooleanArray(N + 1)
    repeat(M) {
        val (u, v, d) = readLine().split(" ").map { it.toInt() }
        graph[u].add(School(v, d, sexList[v - 1]))
        graph[v].add(School(u, d, sexList[u - 1]))
    }
    val pq = PriorityQueue<School>(compareBy { it.weight })
    pq.add(School(1, 0, sexList[0]))
    while (pq.isNotEmpty()) {
        val curr = pq.poll()
        val currSex = curr.sex
        if (!visited[curr.end]) {
            visited[curr.end] = true
            length += curr.weight
            for (nextSchool in graph[curr.end]) {
                if (!visited[nextSchool.end] && nextSchool.sex != currSex) {
                    pq.add(nextSchool)
                }
            }
        }
    }
    if (visited.count { !it } != 1) println(-1) else println(length)
}