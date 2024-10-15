package src.boj.october.Problem14567

import java.util.*

private lateinit var graph: Array<LinkedList<Int>>
private lateinit var inDegree: IntArray
private lateinit var dp: IntArray
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    inDegree = IntArray(n + 1)
    dp = IntArray(n + 1)
    graph = Array(n + 1) { LinkedList() }
    repeat(m) {
        val (a, b) = readLine().split(" ").map { it.toInt() }
        graph[a].add(b)
        inDegree[b]++
    }
    val queue = LinkedList<Int>()
    // 선수과목 -> 들어오는 차수가 없는 애들부터 탐색
    for (i in 1 until inDegree.size) {
        if (inDegree[i] == 0) {
            queue.add(i)
            dp[i] = 1
        }
    }

    while (queue.isNotEmpty()) {
        val curr = queue.poll()
        for (nextNode in graph[curr]) {
            if (--inDegree[nextNode] == 0) {
                queue.add(nextNode)
            }
            dp[nextNode] = dp[curr] + 1
        }
    }
    println(dp.takeLast(n).joinToString(" "))
}