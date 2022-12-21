package boj.december

import java.util.*

private var length = 0
private var lastNode = 0
private var pillar = 0
private var R = 0
data class Node2(val node: Int, var length: Int)
private lateinit var tree: Array<LinkedList<Node2>>
private lateinit var visited: BooleanArray
fun main() = with(Scanner(System.`in`)) {
    val N = nextInt()
    val r = nextInt()
    R = r
    tree = Array(N + 1) { LinkedList<Node2>() }
    visited = BooleanArray(N + 1)
    repeat(N - 1) {
        val a = nextInt()
        val b = nextInt()
        val d = nextInt()
        tree[a].add(Node2(b, d))
        tree[b].add(Node2(a, d))
    }
    checkPillarAndNode(R, 0)
    maxLengthNode(lastNode, 0)
    println("$pillar $length")
}

private fun maxLengthNode(start: Int, sum: Int) {
    if (tree[start].size == 1) {
        length = length.coerceAtLeast(sum)
        return
    }
    visited[start] = true
    for (child in tree[start]) {
        if (!visited[child.node]) {
            maxLengthNode(child.node, sum + child.length)
        }
    }
}


private fun checkPillarAndNode(start: Int, sum: Int) {
    // 기둥이 없는 트리, 기둥만 있는 트리, 기둥과 잎이 다있는 트리
    if ((R == start && 1 < tree[start].size) || (R != start && (tree[start].size == 1 || 2 < tree[start].size))) {
        pillar = sum
        lastNode = start
        return
    }
    visited[start] = true
    for (child in tree[start]) {
        if (!visited[child.node]) {
            visited[child.node] = true
            checkPillarAndNode(child.node, sum + child.length)
        }
    }
}
