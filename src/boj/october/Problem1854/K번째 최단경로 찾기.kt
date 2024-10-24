package src.boj.october.Problem1854

import java.util.*

data class Node(
    val to: Int,
    val cost: Int
): Comparable<Node> {
    override fun compareTo(other: Node): Int {
        return this.cost - other.cost
    }
}
private lateinit var graph: Array<LinkedList<Node>>
private lateinit var dist: Array<PriorityQueue<Int>>
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m, k) = readLine().split(" ").map { it.toInt() }
    graph = Array(n + 1) { LinkedList() }
    dist = Array(n + 1) { PriorityQueue(Collections.reverseOrder())}
    repeat(m) {
        val (from, to, cost) = readLine().split(" ").map { it.toInt() }
        graph[from].add(Node(to, cost))
    }
    dijkstra(k)
    dist.takeLast(n)
        .map { if (it.size == k) it.peek() else -1 }
        .forEach(::println)
}

private fun dijkstra(k: Int) {
    val pq = PriorityQueue<Node>()
    pq.offer(Node(1, 0))
    dist[1].add(0)

    while (pq.isNotEmpty()) {
        val (to, cost) = pq.poll()

        for (nextNode in graph[to]) {
            val newCost = nextNode.cost + cost

            if (dist[nextNode.to].size < k) {
                dist[nextNode.to].add(newCost)
                pq.offer(Node(nextNode.to, newCost))
            } else if (newCost < dist[nextNode.to].peek()) {
                dist[nextNode.to].run {
                    poll()
                    add(newCost)
                }
                pq.offer(Node(nextNode.to, newCost))
            }
        }
    }
}
