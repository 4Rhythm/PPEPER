package boj.july

import java.io.BufferedReader
import java.io.InputStreamReader
var START = 0
var END = 0
private var count = 0
fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val number = readLine().split(' ').map { it.toInt() }
    START = number[0]
    END = number[1]
    // 4와 7로 숫자 만들기
    makeNumber(0,0)
    println(count)
}

private fun makeNumber(number: Int ,depth: Int) {
    if (depth == 10) {
        return
    } else {
        // 만들어진 숫자가 제시한 범위안에 있음
        if (number in START..END) {
            count++
        }
        makeNumber(number * 10 + 4, depth + 1)
        makeNumber(number * 10 + 7, depth + 1)
    }
}
