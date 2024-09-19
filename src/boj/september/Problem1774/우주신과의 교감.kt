package src.boj.september.Problem1774

import java.util.*
import kotlin.math.pow
import kotlin.math.sqrt

private lateinit var parent: IntArray
private lateinit var graph: ArrayList<Pair<Int, Int>>
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    var answer = 0.0
    graph = ArrayList()
    parent = IntArray(n + 1) { it }
    repeat(n) {
        val (x, y) = readLine().split(" ").map { it.toInt() }
        graph.add(Pair(x, y))
    }
    repeat(m) {
        val (a, b) = readLine().split(" ").map { it.toInt() }
        union(a, b)
    }
    val pq = setUniverse()
    while (pq.isNotEmpty()) {
        val curr = pq.poll()
        if (parentNode(curr.first) != parentNode(curr.second)) {
            union(curr.first, curr.second)
            answer += curr.third
        }
    }
    println(String.format("%.2f", answer))
}

private fun union(a: Int, b: Int) {
    val pa = parentNode(a)
    val pb = parentNode(b)
    if (pa < pb) parent[pb] = parent[pa]
    else parent[pa] = pb
}

private fun parentNode(node: Int): Int {
    if (parent[node] != node) {
        parent[node] = parentNode(parent[node])
    }
    return parent[node]
}

private fun setUniverse(): PriorityQueue<Triple<Int, Int, Double>> {
    val pq = PriorityQueue<Triple<Int, Int, Double>>(compareBy { it.third })
    for (i in 0 until graph.size - 1) {
        val universe = graph[i]
        for (j in i + 1 until graph.size) {
            val distance = sqrt(
                (universe.first - graph[j].first).toDouble().pow(2) +
                        (universe.second - graph[j].second).toDouble().pow(2)
            )
            pq.offer(Triple(i + 1, j + 1, distance))
        }
    }
    return pq
}