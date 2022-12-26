package boj.december

import java.util.*

private lateinit var parent: IntArray
private lateinit var visited: BooleanArray
private lateinit var graph: Array<LinkedList<Pair<Int, Int>>>
fun main() = with(Scanner(System.`in`)) {
    var network = 0
    val V = nextInt()
    val E = nextInt()
    val pq = PriorityQueue( compareBy<Pair<Int, Int>> { it.second })
    graph = Array(V + 1) { LinkedList() }
    parent = IntArray(V + 1)
    visited = BooleanArray(V + 1)
    // 부모 초기화
    for (i in 1..V) {
        parent[i] = i
    }
    repeat(E) {
        val A = nextInt()
        val B = nextInt()
        val W = nextInt()
        graph[A].add(Pair(B, W))
        graph[B].add(Pair(A, W))
    }
    // 임의의 정점 1부터 시작
    pq.offer(Pair(1, 0))
    while (pq.isNotEmpty()) {
        val curr = pq.poll()
        if (!visited[curr.first]) {
            // 연결된 정점 방문 확인
            visited[curr.first] = true
            network += curr.second
            for (nextNode in graph[curr.first]) {
                if (!visited[nextNode.first]) {
                    pq.offer(nextNode)
                }
            }
        }
    }
    println(network)
}