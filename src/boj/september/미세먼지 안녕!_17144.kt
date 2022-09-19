package boj.september

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

data class Dust(
    val x: Int,
    val y: Int,
    val amount: Int
)
// 오른쪽, 위, 왼쪽, 아래
private val dx = intArrayOf(0, -1, 0, 1)
private val dy = intArrayOf(1, 0, -1, 0)
private var cleaner = Pair(0, 0)
private lateinit var map: Array<IntArray>
private val top = intArrayOf(0, 1, 2, 3)
private val bottom = intArrayOf(0, 3, 2, 1)
fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val input = readLine().split(' ').map { it.toInt() }
    var T = input[2]
    map = Array(input[0]) { IntArray(input[1]) }
    // map init
    for (i in 0 until input[0]) {
        val value = readLine().split(' ').map { it.toInt() }
        for (j in value.indices) {
            map[i][j] = value[j]
            if (cleaner.first == 0 && value[j] == -1) {
                cleaner = Pair(i, i + 1)
            }
        }
    }
    while (0 < T) {
        // 미세 먼지 확산
        diffusion()
        // 공기청소기 작동
        cleanerStart(cleaner.first, top)
        cleanerStart(cleaner.second, bottom)
        // 시간 지남\
        T--
    }
    val dust = map.sumOf {
        it.sum()
    }
    println(dust + 2)
}

private fun diffusion() {
    val queue: Queue<Dust> = LinkedList()
    for (i in map.indices) {
        for (j in map[i].indices) {
            // 확상될 먼지들 저장 -> dus / 5 만큼 확산 되므로 4보다 큰 먼지들만 확산
            if (4 < map[i][j]) {
                queue.offer(Dust(i, j, map[i][j]))
            }
        }
    }
    while (queue.isNotEmpty()) {
        val dust = queue.poll()
        var count = 0
        for (k in 0 until 4) {
            val dx = dust.x + dx[k]
            val dy = dust.y + dy[k]
            if (isValid(dx, dy) && map[dx][dy] != -1) {
                count++
                map[dx][dy] += dust.amount / 5
            }
        }
        map[dust.x][dust.y] -= count * (dust.amount / 5)
    }
}

private fun cleanerStart(start: Int, spin: IntArray) {
    // deepCopy
    val copyMap = map.map {
        it.copyOf()
    }.toTypedArray()
    var x = start
    var y = 0
    for (i in 0 until 4) {
        while (true) {
            val cx = x + dx[spin[i]]
            val cy = y + dy[spin[i]]
            // 범위를 벗어나면 방향 전환
            if (!isValid(cx, cy)) {
                break
            }
            // 공기청정기에서 나감
            if (copyMap[x][y] == -1) {
                map[cx][cy] = 0
            } else {
                // 공기청정기로 들어가면 종료
                if (copyMap[cx][cy] == -1) {
                    break
                } else {
                    map[cx][cy] = copyMap[x][y]
                }
            }
            // 좌표 이동
            x = cx
            y = cy
        }
    }
}

private fun isValid(x: Int, y: Int): Boolean {
    return 0 <= x && x < map.size && 0 <= y && y < map[0].size
}