package boj.august

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private val dx = intArrayOf(1,-1, 0, 0)
private val dy = intArrayOf(0, 0, 1, -1)
private var DAY = 0
// 안익은 토마토
private var COUNT = 0
data class Tomato2(
    var x: Int,
    var y: Int
)
fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val input = readLine().split(' ').map { it.toInt() }
    val tomato = Array(input[1]) { IntArray(input[0]) }
    // 익은 토마토들
    val list: Queue<Tomato2> = LinkedList()
    // 토마토 init
        for (x in 0 until input[1]) {
            val value = readLine().split(' ').map { it.toInt() }
            // 배열
            for (y in value.indices) {
                tomato[x][y] = value[y]
                // 익은 토마토
                if (tomato[x][y] == 1) {
                    list.offer(Tomato2(x, y))
                } else if (tomato[x][y] == 0) {
                    COUNT++
            }
        }
    }
    // 토마토 익는중
    dayPass(tomato, list)
    if (COUNT == 0) {
        println(DAY)
    } else {
        println(-1)
    }
}

private fun dayPass(tomato: Array<IntArray>, list: Queue<Tomato2>) {
    while (COUNT > 0 && list.isNotEmpty()) {
        repeat(list.size) {
            val curr = list.poll()
            // 위아래상하좌우
            for (i in 0 until 4) {
                val dx = curr.x + dx[i]
                val dy = curr.y + dy[i]
                // 안익은 토마토가 익게됨
                if (isValid(dx, dy, tomato) && tomato[dx][dy] == 0) {
                    COUNT--
                    tomato[dx][dy] = 1
                    list.offer(Tomato2(dx, dy))
                }
            }
        }
        // 하루가 지남
        DAY++
    }
}

private fun isValid(x: Int, y: Int, tomato: Array<IntArray>): Boolean {
    return x >= 0 && x < tomato.size && y >= 0 && y < tomato[0].size
}