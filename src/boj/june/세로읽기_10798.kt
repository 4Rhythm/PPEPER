package boj.june

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    var answer = ""
    val array = Array(5) { ArrayList<Any>() }
    for (i in 0 until 5) {
        val str = readLine()
        for (j in str.indices) {
            array[i].add(str[j])
        }
    }
    // 최대 15개 중 가장 길게 입력받은 길이
    val length = array.maxByOrNull { it.size }!!.size
    // 출력 -> 세로
    for (i in 0 until length) {
        for (j in 0 until 5) {
            if (array[j].size < i + 1) {
                continue
            }
            answer += array[j][i]
        }
    }
    print(answer)
}
