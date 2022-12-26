package boj.december

import java.util.*

private lateinit var parent: IntArray
fun main() = with(Scanner(System.`in`)) {
    var weightSum = 0
    val V = nextInt()
    val E = nextInt()
    val pq = PriorityQueue( compareBy<Triple<Int, Int, Int>> { it.third })
    parent = IntArray(V + 1)
    repeat(E) {
        val A = nextInt()
        val B = nextInt()
        val W = nextInt()
        pq.offer(Triple(A, B, W))
    }
    // 부모 초기화
    for (i in 1..V) {
        parent[i] = i
    }
    while (pq.isNotEmpty()) {
        val curr = pq.poll()
        // 부모가 같으면 사이클 형성
        if (getParentNode(curr.first) != getParentNode(curr.second)) {
            unionNode(curr.first, curr.second)
            weightSum += curr.third
        }
    }
    println(weightSum)
}

private fun unionNode(x: Int, y: Int) {
    val px = getParentNode(x)
    val py = getParentNode(y)
    if (px < py) parent[py] = px
    else parent[px] = py
}

private fun getParentNode(node: Int): Int {
    if (parent[node] != node) {
        parent[node] = getParentNode(parent[node])
    }
    return parent[node]
}
