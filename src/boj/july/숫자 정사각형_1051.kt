package boj.july

import java.io.BufferedReader
import java.io.InputStreamReader

private var MAX = 1
lateinit var MATRIX: Array<IntArray>
fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val input = readLine().split(' ').map { it.toInt() }
    MATRIX = Array(input[0]) { IntArray(input[1]) }
    // matrix init
    for (i in MATRIX.indices) {
        val numbers = readLine().map { it }
        for (j in numbers.indices) {
            MATRIX[i][j] = numbers[j] - '0'
        }
    }
    // 정사각형 찾기
    for (i in MATRIX.indices) {
        for (j in MATRIX[i].indices) {
            square(i, j, MATRIX[i][j])
        }
    }
    println(MAX * MAX)
}

private fun square(i: Int, j: Int, value: Int) {
    var index = 1
    // MATRIX 범위까지
    while (index + i < MATRIX.size && index + j < MATRIX[i].size) {
        if (MATRIX[index + i][j] == value && MATRIX[i][index + j] == value && MATRIX[index + i][index + j] == value) {
            MAX = MAX.coerceAtLeast(index + 1)
        }
        index++
    }
}


