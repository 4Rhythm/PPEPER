package src.boj.august.Problem14226

import java.util.LinkedList

private lateinit var visited: Array<BooleanArray>
fun main() = with(System.`in`.bufferedReader()) {
    val s = readLine().toInt()
    visited = Array(1001) { BooleanArray(1001) }
    println(bfs(s))
}

private fun bfs(target: Int): Int {
    val q = LinkedList<Pair<Int, Int>>()
    q.add(Pair(1, 0))
    visited[1][0] = true
    var time = 0
    while (q.isNotEmpty()) {
        val size = q.size
        repeat(size) {
            val (curr, clip) = q.poll()
            val sum = curr + clip
            // 복사하기
            if (sum <= 1000) {
                q.add(Pair(curr, curr))
                visited[curr][curr] = true
            }
            // 붙여넣기
            if (clip != 0) {
                if (sum == target) {
                    return time + 1
                }
                if (check(sum, clip)) {
                    visited[sum][clip] = true
                    q.add(Pair(sum, clip))
                }
            }
            // 지우기
            if (1 < clip) {
                if (curr - 1 == target) {
                    return time + 1
                }
                if (check(curr - 1, clip)) {
                    visited[curr - 1][clip] = true
                    q.add(Pair(curr - 1, clip))
                }
            }
        }
        time++
    }
    return 0
}

private fun check(curr: Int, clip: Int): Boolean {
    if (curr !in 1..1000) return false
    if (visited[curr][clip]) return false
    return true
}
