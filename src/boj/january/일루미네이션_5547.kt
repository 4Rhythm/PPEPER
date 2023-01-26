package boj.january

import java.util.*

private lateinit var house: Array<IntArray>
private lateinit var visited: Array<BooleanArray>
private val dx1 = intArrayOf(-1, -1, 0, 0, 1, 1)
private val dy1 = intArrayOf(1, 0, -1, 1, 1, 0)
private val dx2 = intArrayOf(-1, -1, 0, 0, 1, 1)
private val dy2 = intArrayOf(-1, 0, -1, 1, -1, 0)
fun main() = with(System.`in`.bufferedReader()) {
    val (W, H) = readLine().split(" ").map { it.toInt() }
    val queue = LinkedList<Pair<Int, Int>>()
    house = Array(H + 2) { IntArray(W + 2) }
    for (i in 1..H) {
        val input = readLine().split(" ").map { it.toInt() }
        for (j in input.indices) {
            house[i][j + 1] = input[j]
        }
    }
    visited = Array(H + 2) { BooleanArray(W + 2) }
    // 외벽을 돌면서 벽면 확인
    queue.offer(Pair(0, 0))
    visited[0][0] = true
    while (queue.isNotEmpty()) {
        val curr = queue.poll()
        // x의 홀짝에 따라 탐색이 다름
        val dx = if (curr.first % 2 != 0) dx1 else dx2
        val dy = if (curr.first % 2 != 0) dy1 else dy2
        for (i in dx.indices) {
            val cx = curr.first + dx[i]
            val cy = curr.second + dy[i]
            if (checkBoard(cx, cy)){
                if (!visited[cx][cy]) {
                    if (house[cx][cy] == 0) {
                        visited[cx][cy] = true
                        queue.offer(Pair(cx, cy))
                    }
                }
            }
        }
    }
    println(calculateWall(W, H))
}
private fun calculateWall(W: Int, H: Int): Int {
    var count = 0
    for (i in 1..H) {
        val dx = if (i % 2 != 0) dx1 else dx2
        val dy = if (i % 2 != 0) dy1 else dy2
        for (j in 1..W) {
            if (house[i][j] == 1) {
                for (k in dx.indices) {
                    val cx = i + dx[k]
                    val cy = j + dy[k]
                    if (visited[cx][cy]) count++
                }
            }
        }
    }
    return count
}
private fun checkBoard(x: Int, y: Int): Boolean {
    return x in house.indices && y in house[0].indices
}