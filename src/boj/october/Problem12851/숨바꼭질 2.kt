package src.boj.october.Problem12851

import java.util.*

private var minTime = Int.MAX_VALUE
private var count = 0
private lateinit var visited: BooleanArray
fun main() = with(System.`in`.bufferedReader()) {
    val (n, k) = readLine().split(" ").map { it.toInt() }
    visited = BooleanArray(200_001)
    search(n, k)
    println(minTime)
    println(count)
}

private fun search(n: Int, k: Int) {
    val queue: Queue<Pair<Int, Int>> = LinkedList()
    queue.add(Pair(n, 0))
    visited[n] = true
    while (queue.isNotEmpty()) {
        val (pos, time) = queue.poll()
        visited[pos] = true
        if (pos == k) {
            if (time < minTime) {
                minTime = time
                count = 1
            }
            if (time == minTime) {
                count += 1
            }
            continue
        }
        if (pos + 1 < visited.size && !visited[pos + 1]) {
            queue.add(Pair(pos + 1, time + 1))
        }
        if (0 <= pos - 1 && !visited[pos - 1]) {
            queue.add(Pair(pos - 1, time + 1))
        }
        if (pos * 2 < visited.size && !visited[pos * 2]) {
            queue.add(Pair(pos * 2, time + 1))
        }
    }
}
