package src.boj.september.Problem20010

import java.util.LinkedList
import java.util.PriorityQueue

private lateinit var graph: Array<LinkedList<Pair<Int, Int>>>
private lateinit var parent: IntArray
private lateinit var visited: BooleanArray
private var minDistance = 0
private var maxDistance = 0
private var start = 0
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    graph = Array(n) { LinkedList() }
    parent = IntArray(n) { it }
    visited = BooleanArray(n)

    val pq = PriorityQueue<Triple<Int, Int, Int>>(compareBy { it.third })

    repeat(m) {
        val (a, b, c) = readLine().split(" ").map { it.toInt() }
        pq.add(Triple(a, b, c))
    }

    while (pq.isNotEmpty()) {
        val (a, b, c) = pq.poll()
        if (parentNode(a) != parentNode(b)) {
            minDistance += c
            union(a, b)
            graph[a].add(Pair(b, c))
            graph[b].add(Pair(a, c))
        }
    }
    visited[0] = true
    dfs(0, 0)
    maxDistance = 0

    visited.fill(false)
    visited[start] = true
    dfs(start, 0)
    println(minDistance)
    println(maxDistance)
}

private fun union(a: Int, b: Int) {
    val pa = parentNode(a)
    val pb = parentNode(b)
    if (pa < pb) parent[pb] = pa
    else parent[pa] = pb
}

private fun parentNode(node: Int): Int {
    if (parent[node] != node) {
        parent[node] = parentNode(parent[node])
    }
    return parent[node]
}

private fun dfs(node: Int, cost: Int) {
    if (maxDistance < cost) {
        maxDistance = cost
        start = node
    }
    for (nextNode in graph[node]) {
        if (visited[nextNode.first]) continue
        visited[nextNode.first] = true
        dfs(nextNode.first, cost + nextNode.second)
    }
}
