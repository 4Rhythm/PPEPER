package src.boj.october.Problem11725

import java.util.*

private lateinit var tree: Array<LinkedList<Int>>
private lateinit var visited: BooleanArray
private lateinit var parent: IntArray
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    tree = Array(n + 1) { LinkedList() }
    visited = BooleanArray(n + 1)
    parent = IntArray(n + 1)
    repeat(n - 1) {
        val (a, b) = readLine().split(" ").map { it.toInt() }
        tree[a].add(b)
        tree[b].add(a)
    }
    findParent(1)
    parent.takeLast(n - 1).forEach(::println)
}

private fun findParent(root: Int) {
    visited[root] = true
    for (child in tree[root]) {
        if (!visited[child]) {
            visited[child] = true
            parent[child] = root
            findParent(child)
        }
    }
}