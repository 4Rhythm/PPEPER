package boj.december

import java.util.PriorityQueue

data class Field(val from: Int, val to: Int, val weight: Int)
private lateinit var parent: IntArray
fun main() = with(System.`in`.bufferedReader()) {
    var answer = 0
    val N = readLine().toInt()
    val list = IntArray(N + 1)
    parent = IntArray(N + 1) { it }
    // 우물을 팔 때 드는 비용
    repeat(N) {
        val value = readLine().toInt()
        list[it + 1] = value
    }
    val pq = PriorityQueue<Field>(compareBy { it.weight })
    for (i in 1..N) {
        val input = readLine().split(" ").map { it.toInt() }
        for (j in i..N) {
            if (i == j) pq.offer(Field(0, i, list[i]))
            else pq.offer(Field(i, j, input[j - 1]))
        }
    }
    while (pq.isNotEmpty()) {
        val curr = pq.poll()
        if (getParent(curr.from) != getParent(curr.to)) {
            unionParent(curr.from, curr.to)
            answer += curr.weight
        }
    }
    println(answer)
}

private fun unionParent(a: Int, b: Int) {
    val pa = parent[a]
    val pb = parent[b]
    if (pa < pb) parent[pb] = pa
    else parent[pa] = pb
}

private fun getParent(node: Int): Int {
    if (parent[node] != node) {
        parent[node] = getParent(parent[node])
    }
    return parent[node]
}