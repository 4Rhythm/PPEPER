package boj.august

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private val dx = intArrayOf(1,-1, 0, 0, 0, 0)
private val dy = intArrayOf(0, 0, 1, -1, 0, 0)
private val dz = intArrayOf(0, 0, 0, 0, 1, -1)
private var DAY = 0
// 안익은 토마토
private var COUNT = 0
data class Tomato(
    var x: Int,
    var y: Int,
    var z: Int
)
fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val input = readLine().split(' ').map { it.toInt() }
    val tomato = Array(input[1]) { Array(input[0]) { IntArray(input[2])} }
    // 익은 토마토들
    val list: Queue<Tomato> = LinkedList()
    // 토마토 init
    for (z in 0 until input[2]) {
        for (x in 0 until input[1]) {
            val value = readLine().split(' ').map { it.toInt() }
            // 배열
            for (y in value.indices) {
                tomato[x][y][z] = value[y]
                // 익은 토마토
                if (tomato[x][y][z] == 1) {
                    list.offer(Tomato(x, y, z))
                } else if (tomato[x][y][z] == 0) {
                    COUNT++
                }
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

private fun dayPass(tomato: Array<Array<IntArray>>, list: Queue<Tomato>) {
    while (COUNT > 0 && list.isNotEmpty()) {
        repeat(list.size) {
            val curr = list.poll()
            // 위아래상하좌우
            for (i in 0 until 6) {
                val dx = curr.x + dx[i]
                val dy = curr.y + dy[i]
                val dz = curr.z + dz[i]
                // 안익은 토마토가 익게됨
                if (isValid(dx, dy, dz, tomato) && tomato[dx][dy][dz] == 0) {
                    COUNT--
                    tomato[dx][dy][dz] = 1
                    list.offer(Tomato(dx, dy, dz))
                }
            }
        }
        // 하루가 지남
        DAY++
    }
}

private fun isValid(x: Int, y: Int, z: Int, tomato: Array<Array<IntArray>>): Boolean {
    return x >= 0 && x < tomato.size && y >= 0 && y < tomato[0].size && z >= 0 && z < tomato[0][0].size
}
