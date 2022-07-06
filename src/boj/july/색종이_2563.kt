package boj.july

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val area = Array(100) { IntArray(100) { 0 } }
    val count = readLine().toInt()
    // 종이 넓이를 하나씩 더해가며 겹치는 부분만 삭제
    for (i in 0 until count) {
        val position = readLine().split(' ').map { it.toInt() }
        // 넓이가 확인된
        for (x in position[1] - 1 until position[1] + 9) {
            for (y in position[0] - 1 until position[0] + 9) {
                if (area[x][y] == 0) {
                    area[x][y] = 1
                }
            }
        }
    }
    println(area.sumOf { it.sum() })
}
