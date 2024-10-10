package src.boj.october.Problem1939

import java.util.*

private lateinit var graph: Array<LinkedList<Pair<Int, Int>>>
private lateinit var visited: BooleanArray
private lateinit var area: List<Int>
private var min = Int.MAX_VALUE
private var max = Int.MIN_VALUE
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    graph = Array(n + 1) { LinkedList() }
    visited = BooleanArray(n + 1)
    repeat(m) {
        val (a, b, c) = readLine().split(" ").map { it.toInt() }
        graph[a].add(Pair(b, c))
        graph[b].add(Pair(a, c))
        min = min.coerceAtMost(c)
        max = max.coerceAtLeast(c)
    }
    area = readLine().split(" ").map { it.toInt() }
    println(search())
}

// 가능한 최대의 중량을 확인
private fun search(): Int {
    var left = min
    var right = max

    while (left <= right) {
        val mid = (left + right) / 2

        // 이동이 가능하면 더 무게를 무거운 쪽까지 확인
        if (canMove(mid)) {
            left = mid + 1
        } else {
            right = mid - 1
        }
    }
    return right
}

private fun canMove(weight: Int): Boolean {
    visited.fill(false)
    val queue: Queue<Int> = LinkedList()
    queue.add(area[0])
    visited[area[0]] = true
    while (queue.isNotEmpty()) {
        val curr = queue.poll()
        for (nextNode in graph[curr]) {
            if (nextNode.second < weight) continue
            if (visited[nextNode.first]) continue
            if (nextNode.first == area[1]) return true
            queue.add(nextNode.first)
            visited[nextNode.first] = true
        }
    }
    return false
}
