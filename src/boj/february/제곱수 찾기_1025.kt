package boj.february

import kotlin.math.sqrt

private var answer = -1
private var n = 0
private var m = 0
fun main() = with(System.`in`.bufferedReader()) {
    val (a, b) = readLine().split(" ").map { it.toInt() }
    n = a
    m = b
    val list = Array(n) { IntArray(m) }
    for (i in 0 until a) {
        readLine().forEachIndexed { index, c ->
            val input = c - '0'
            list[i][index] = input
        }
    }
    // 모든 경우의 수를 확인해봐야한다.
    for (i in list.indices) {
        for (j in list[0].indices) {
            for (x in -n until n) {
                for (y in -m until m) {
                    if (x == 0 && y == 0) continue
                    var dx = i
                    var dy = j
                    var buffer = 0
                    while (checkRange(dx, dy)) {
                        buffer *= 10
                        buffer += list[dx][dy]
                        checkNumber(buffer)
                        dx += x
                        dy += y
                    }
                }
            }
        }
    }
    println(answer)
}

private fun checkNumber(number: Int) {
    val temp = sqrt(number.toDouble()).toInt()
    if (temp * temp == number) {
        answer = answer.coerceAtLeast(number)
    }
}
private fun checkRange(x: Int, y: Int): Boolean {
    return (x in 0 until n && y in 0 until m)
}