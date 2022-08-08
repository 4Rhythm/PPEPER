package boj.august

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private val dx = intArrayOf(1, -1, 0, 0)
private val dy = intArrayOf(0, 0, 1, -1)
private var COUNT = Int.MAX_VALUE
data class Maze(
    var x: Int,
    var y: Int,
    var count: Int
)
fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val input = readLine().split(' ').map { it.toInt() }
    val map = Array(input[0]) { IntArray(input[1]) }
    // map init
    for (x in 0 until input[0]) {
        val line = readLine()
        line.forEachIndexed { y, c ->
            map[x][y] = c - '0'
        }
    }
    val visited = Array(input[0]) { BooleanArray(input[1]) }
    maze(map, visited)
    println(COUNT)
}

private fun maze(map: Array<IntArray>, visited: Array<BooleanArray>) {
    val queue: Queue<Maze> = LinkedList()
    queue.offer(Maze(0,0, 0))
    visited[0][0] = true
    while (queue.isNotEmpty()) {
        val v = queue.poll()
        for (i in 0 until 4) {
            val dx = v.x + dx[i]
            val dy = v.y + dy[i]
            if (isValid(dx, dy, map) && map[dx][dy] == 1 && !visited[dx][dy]) {
                // dx dy가 미로의 끝나는 지점
                if (dx == map.size - 1 && dy == map[0].size - 1) {
                    // 가장 짧은 방법으로 바꿈 -> 현재 이동하는 비용 + 처음 시작도 1칸 이동이라 하였으므로 +2
                    COUNT = COUNT.coerceAtMost(v.count + 2)
                } else {
                    visited[dx][dy] = true
                    queue.offer(Maze(dx, dy, v.count + 1))
                }
            }
        }
    }
}

private fun isValid(x: Int, y: Int, map: Array<IntArray>): Boolean {
    return x >= 0 && x < map.size && y >= 0 && y < map[0].size
}
