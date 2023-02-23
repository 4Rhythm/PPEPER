package boj.february

import kotlin.math.min

private var answer = Int.MAX_VALUE
private val list = IntArray(2)
private const val INF = 10000
private lateinit var graph: Array<IntArray>
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    graph = Array(n + 1) { IntArray(n + 1) { INF } }
    for (i in 1 until graph.size) {
        for (j in 1 until graph.size) {
            if (i == j) graph[i][j] = 0
        }
    }
    for (i in 0 until m) {
        val (from, to) = readLine().split(" ").map { it.toInt() }
        graph[from][to] = 1
        graph[to][from] = 1
    }
    // 경유하는 지점
    for (k in 1 until graph.size) {
        // 출발 노드
        for (from in 1 until graph.size) {
            // 도착 노드
            for (to in 1 until graph.size) {
                graph[from][to] = graph[from][to].coerceAtMost(graph[from][k] + graph[k][to])
            }
        }
    }
    // 치킨집을 세울 지역의 수 index 1부터 시작
    chickenBuilding()
    println("${list[0]} ${list[1]} $answer")
}

private fun chickenBuilding() {
    for (i in 1 until graph.size) {
        for (j in i + 1 until graph.size) {
            var distance = 0
            for (house in 1 until graph.size) {
                // 거리를 구할 필요 없다.
                if (house == i || house == j) continue
                distance += min(graph[house][i] * 2, graph[house][j] * 2)
            }
            // 거리 확인
            if (distance < answer) {
                answer = distance
                list[0] = i
                list[1] = j
            }
        }
    }
}