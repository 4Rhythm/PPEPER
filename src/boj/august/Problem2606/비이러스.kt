package src.boj.august.Problem2606

import java.util.LinkedList

private lateinit var graph: Array<LinkedList<Int>>
private lateinit var visited: BooleanArray
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val m = readLine().toInt()
    graph = Array(n + 1) { LinkedList() }
    visited = BooleanArray(n + 1)
    repeat(m) {
        val (a, b) = readLine().split(" ").map { it.toInt() }
        graph[a].add(b)
        graph[b].add(a)
    }
    println(bfs())
    dfs(1)
    println(visited.count { it } - 1)
}

// bfs 사용
private fun bfs(): Int {
    var count = 0
    val q = LinkedList<Int>()
    q.add(1)
    visited[1] = true
    while (q.isNotEmpty()) {
        val curr = q.poll()
        count++
        for (child in graph[curr]) {
            if (visited[child]) continue
            visited[child] = true
            q.add(child)
        }
    }
    return count - 1
}

// dfs 사용
private fun dfs(node: Int) {
    visited[node] = true
    for (child in graph[node]) {
        if (visited[child]) continue
        dfs(child)
    }
}