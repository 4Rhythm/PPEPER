package boj.august

import java.io.BufferedReader
import java.io.InputStreamReader

private var MAX = -1000000000
private var MIN = 1000000000
lateinit var numbers: List<Int>
lateinit var opers: IntArray
fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val size = readLine().toInt()
    // 숫자들
    numbers = readLine().split(' ').map { it.toInt() }
    // 연산자 개수
    opers = readLine().split(' ').map { it.toInt() }.toIntArray()
    calculate(numbers[0], 1)
    println(MAX)
    println(MIN)
}

private fun calculate(value: Int, index: Int) {
    if (index == numbers.size) {
        MAX = MAX.coerceAtLeast(value)
        MIN = MIN.coerceAtMost(value)
        return
    }
    for (i in opers.indices) {
        // 연산자가 남아 있음
        if (opers[i] != 0) {
            // 연산자 하나 제거
            opers[i]--
            when (i) {
                0 -> calculate(value + numbers[index], index + 1)
                1 -> calculate(value - numbers[index], index + 1)
                2 -> calculate(value * numbers[index], index + 1)
                3 -> calculate(value / numbers[index], index + 1)
            }
            // 제거한 연산자 다시 더해줌
            opers[i]++
        }
    }
}
