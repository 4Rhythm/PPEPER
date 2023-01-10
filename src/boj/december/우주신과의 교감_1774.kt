package boj.december

import java.util.PriorityQueue
import kotlin.math.pow
import kotlin.math.sqrt

private lateinit var parent: IntArray
data class Universe(val node: Int, val end: Int, val weight: Double)
fun main() = with(System.`in`.bufferedReader()) {
    val (N, M) = readLine().split(" ").map { it.toInt() }
    val list = ArrayList<Pair<Int, Int>>()
    var answer = 0.0
    parent = IntArray(N + 1)
    repeat(N) { i -> parent[i] = i }
    repeat(N) {
        val (x, y) = readLine().split(" ").map { it.toInt() }
        list.add(Pair(x, y))
    }
    // 연결되어 있는 우주신들
    repeat(M) {
        val (a, b) = readLine().split(" ").map { it.toInt() }
        unionParent(a, b)
    }
    // 우주신들간의 거리 초기화
    val pq = setUniverse(list)
    while (pq.isNotEmpty()) {
        val curr = pq.poll()
        // 연결 X
        if (parentNode(curr.node) != parentNode(curr.end)) {
            unionParent(curr.node, curr.end)
            answer += curr.weight
        }
    }
    println(String.format("%.2f", answer))
}

private fun setUniverse(list: ArrayList<Pair<Int, Int>>): PriorityQueue<Universe> {
    val pq = PriorityQueue<Universe>(compareBy { it.weight })
    for (i in 0 until list.size - 1) {
        val universe = list[i]
        for (j in i + 1 until list.size) {
            val distance = sqrt(
                (universe.first - list[j].first).toDouble().pow(2.0) +
                        (universe.second - list[j].second).toDouble().pow(2.0)
            )
            pq.offer(Universe(i + 1, j + 1, distance))
        }
    }
    return pq
}

private fun unionParent(a: Int, b: Int) {
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
