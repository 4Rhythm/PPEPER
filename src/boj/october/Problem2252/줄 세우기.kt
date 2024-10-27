package src.boj.october.Problem2252

import java.util.*

private lateinit var list: Array<LinkedList<Int>>
private lateinit var inDegree: IntArray
fun main() = with(System.`in`.bufferedReader()) {
    val answer = StringBuilder()
    val (n, m) = readLine().split(" ").map { it.toInt() }
    list = Array(n + 1) { LinkedList() }
    inDegree = IntArray(n + 1)
    repeat(m) {
        val (a, b) = readLine().split(" ").map { it.toInt() }
        list[a].add(b)
        list[b].add(a)
        inDegree[b]++
    }
    val q = LinkedList<Int>()
    for (i in 1..n) {
        if (inDegree[i] == 0) {
            q.add(i)
        }
    }
    while (q.isNotEmpty()) {
        val curr = q.poll()
        answer.append("$curr ")
        for (next in list[curr]) {
            if (--inDegree[next] == 0) {
                q.add(next)
            }
        }
    }
    println(answer)
}