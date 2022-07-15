package boj.july

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.abs

private var count = Int.MAX_VALUE
lateinit var channel: String
private var numbers = mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    channel = readLine()
    val input = readLine()
    // 고장난 리모컨 버튼 삭제
    if (input.toInt() != 0) {
        val broken = readLine().split(' ').map { it.toInt() }
        broken.forEach {
            numbers.remove(it)
        }
    }
    // +, -만 이용하는 경우
    count = count.coerceAtMost(abs(channel.toInt() - 100))
    checkNumbers()
    println(count)
}

private fun checkNumbers() {
    // 버튼이 고장이 안났으면 해당 채널로 이동하는 것과 비교
    if (numbers.size == 10) {
        count = count.coerceAtMost(channel.length)
    } else {
        for (number in 0..999999) {
            if (isValid(number.toString())) {
                count = count.coerceAtMost(abs(channel.toInt() - number) + number.toString().length)
            }
        }
    }
}
private fun isValid(number: String): Boolean {
    number.forEach {
        if (!numbers.contains(it - '0')) {
            return false
        }
    }
    return true
}