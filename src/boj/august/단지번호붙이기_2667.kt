package boj.august

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayList

private val dx = intArrayOf(1, -1, 0, 0)
private val dy = intArrayOf(0, 0, 1, -1)
fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val size = readLine().toInt()
    val map = Array(size) { IntArray(size) }
    val visited = Array(size) { BooleanArray(size) }
    val list = ArrayList<Int>()
    // map init
    for (x in 0 until size) {
        val input = readLine()
        input.forEachIndexed { y, c ->
            map[x][y] = c - '0'
        }
    }
    for (i in map.indices) {
        for (j in map[i].indices) {
            if (!visited[i][j] && map[i][j] == 1) {
                visited[i][j] = true
                checkMap(map, visited, list, i, j)
            }
        }
    }
    val count = list.size
    list.sort()
    println(count)
    list.forEach {
        println(it)
    }
}

private fun checkMap(map: Array<IntArray>, visited: Array<BooleanArray>, list: ArrayList<Int>, x: Int, y: Int) {
    var count = 1
    val queue: Queue<Pair<Int, Int>> = LinkedList()
    queue.offer(Pair(x, y))
    while (queue.isNotEmpty()) {
        val v = queue.poll()
        for (i in 0 until 4) {
            val dx = v.first + dx[i]
            val dy = v.second + dy[i]
            if (isValid(dx, dy, map)) {
                if (!visited[dx][dy] && map[dx][dy] == 1) {
                    visited[dx][dy] = true
                    queue.offer(Pair(dx, dy))
                    count++
                }
            }
        }
    }
    list.add(count)
}

private fun isValid(x: Int, y: Int, map: Array<IntArray>): Boolean {
    return x >= 0 && x < map.size && y >= 0 && y < map.size
}
