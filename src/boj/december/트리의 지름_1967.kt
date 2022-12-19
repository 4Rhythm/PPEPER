package boj.december

import java.util.*

data class Node(val end: Int, val weight: Int)
private lateinit var tree: Array<LinkedList<Node>>
private lateinit var visited: BooleanArray
private var maxLeafNode = 0
private var diameter = Int.MIN_VALUE
fun main() = with(Scanner(System.`in`)){
    val size = nextInt()
    tree = Array(size + 1) { LinkedList<Node>() }
    visited = BooleanArray(size + 1)
    repeat(size - 1) {
        val from = nextInt()
        val to = nextInt()
        val weight = nextInt()
        tree[from].add(Node(to, weight))
        tree[to].add(Node(from, weight))
    }
    maxLength(1, 0)
    diameter = Int.MIN_VALUE
    visited.fill(false)
    maxLength(maxLeafNode, 0)
    println(diameter)
}

private fun maxLength(start: Int, weight: Int) {
    // 가장 멀리 있는 노드
    if (diameter < weight) {
        diameter = weight
        maxLeafNode = start
    }
    visited[start] = true
    for (child in tree[start]) {
        if (!visited[child.end]) {
            maxLength(child.end, weight + child.weight)
        }
    }
}
