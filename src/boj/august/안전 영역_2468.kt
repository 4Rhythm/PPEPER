package boj.august

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private var MAX = Int.MIN_VALUE
private var MIN = Int.MAX_VALUE
private val dx = intArrayOf(1, -1, 0, 0)
private val dy = intArrayOf(0, 0, 1, -1)
private var answer = 1
fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val size = readLine().toInt()
    val map = Array(size) { IntArray(size) }
    // map init
    for (x in 0 until size) {
        val input = readLine().split(' ').map { it.toInt() }
        map[x] = input.toIntArray()
        input.forEach {
            MAX = MAX.coerceAtLeast(it)
            MIN = MIN.coerceAtMost(it)
        }
    }
    // 안전 영역 -> MIN ~ MAX 높이까지 영역 구하기
    for (height in (MIN..MAX)) {
        var count = 0
        val visited = Array(size) { BooleanArray(size) }
        for (i in map.indices) {
            for (j in map[i].indices) {
                if (!visited[i][j] && map[i][j] > height) {
                    search(map, visited, height, i, j)
                    count++
                }
            }
        }
        // 최대값 업데이트
        answer = count.coerceAtLeast(answer)
    }
    println(answer)
}

private fun search(map: Array<IntArray>, visited: Array<BooleanArray>, height: Int, x: Int, y: Int) {
    val queue: Queue<Pair<Int, Int>> = LinkedList()
    queue.offer(Pair(x, y))
    while (queue.isNotEmpty()) {
        val v = queue.poll()
        for (i in 0 until 4) {
            val dx = v.first + dx[i]
            val dy = v.second + dy[i]
            if (isValid(map, dx, dy)) {
                if (!visited[dx][dy] && map[dx][dy] > height) {
                    visited[dx][dy] = true
                    queue.offer(Pair(dx, dy))
                }
            }
        }
    }
}

private fun isValid(map: Array<IntArray>, x: Int, y: Int): Boolean {
    return x >= 0 && x < map.size && y >= 0 && y < map.size
}
