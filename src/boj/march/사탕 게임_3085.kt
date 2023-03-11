package boj.march

import kotlin.math.max

private lateinit var map: Array<CharArray>
private var answer = 0
private val dx = intArrayOf(1, -1, 0, 0)
private val dy = intArrayOf(0, 0, 1, -1)

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    map = Array(n) { readLine().toCharArray() }
    getMax()
    for (i in map.indices) {
        for (j in map.indices) {
            swap(i, j)
        }
    }
    println(answer)
}

private fun swap(x: Int, y: Int) {
    for (i in 0 until 4) {
        val nx = x + dx[i]
        val ny = y + dy[i]
        if (nx < 0 || map.size <= nx || ny < 0 || map.size <= ny) continue
        if (map[x][y] == map[nx][ny]) continue
        // 스위치
        map[x][y] = map[nx][ny].also { map[nx][ny] = map[x][y] }
        getMax()
        map[x][y] = map[nx][ny].also { map[nx][ny] = map[x][y] }
    }
}

private fun getMax() {
    for (i in map.indices) {
        for (j in map.indices) {
            answer = answer.coerceAtLeast(checkLength(i, j));
        }
    }
}

private fun checkLength(x: Int, y: Int): Int {
    var curr = map[x][y]
    var row = 0
    var col = 0
    // col
    for (i in x until map.size) {
        if (curr == map[i][y]) {
            col++
        } else {
            break
        }
    }
    curr = map[x][y]
    // row
    for (i in y until map.size) {
        if (curr == map[x][i]) {
            row++
        } else {
            break
        }
    }
    return max(row, col)
}
