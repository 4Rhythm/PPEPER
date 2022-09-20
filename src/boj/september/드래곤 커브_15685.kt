package boj.september

import java.io.BufferedReader
import java.io.InputStreamReader

private val dx = intArrayOf(0, -1, 0, 1)
private val dy = intArrayOf(1, 0, -1, 0)
private lateinit var map: Array<IntArray>
fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    map = Array(101) { IntArray(101) }
    val size = readLine().toInt()
    for (i in 0 until size) {
        val curve = readLine().split(' ').map { it.toInt() }
        draw(curve)
    }
    var count = 0
    // 범위를 지정
    for (i in map.indices) {
        for (j in map.indices) {
            if (map[i][j] == 1) {
                if (checkSquare(i, j)) {
                    count++
                }
            }
        }
    }
    println(count)
}

private fun draw(curve: List<Int>) {
    var x = curve[1]
    var y = curve[0]
    map[x][y] = 1
    val curves = ArrayList<Int>()
    // 처음 이동 방향
    curves.add(curve[2])
    getCurveList(curves, 0, curve[3])
    for (i in curves.indices) {
        x += dx[curves[i]]
        y += dy[curves[i]]
        map[x][y] = 1
    }
}

private fun getCurveList(curves: ArrayList<Int>, start: Int, generation: Int) {
    if (start == generation) {
        return
    }
    for (i in curves.size - 1 downTo 0) {
        curves.add((curves[i] + 1) % 4)
    }
    getCurveList(curves, start + 1, generation)
}

private fun isValid(x: Int, y: Int): Boolean {
    return 0 <= x && x < map.size && 0 <= y && y < map[0].size
}

private fun checkSquare(x: Int, y: Int): Boolean {
    return isValid(x + 1, y + 1) && map[x][y] == 1 && map[x + 1][y] == 1 && map[x][y + 1] == 1 && map[x + 1][y + 1] == 1
}