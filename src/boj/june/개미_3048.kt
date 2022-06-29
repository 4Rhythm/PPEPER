package boj.june

import java.io.BufferedReader
import java.io.InputStreamReader

data class Direction(
    var value: String,
    var direction: Int
)
fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val length = readLine()!!.split(' ').map { it.toInt() }
    val first = readLine().map { Direction(it.toString(), 1) }.reversed()
    val second = readLine().map { Direction(it.toString(), -1) }
    var time = readLine().toInt()
    // 처음 순서
    val order = (first + second).toMutableList()
    while (time != 0) {
        var i = 0
        while (i < order.size - 1) {
            // 방향이 반대인경우
            if (order[i].direction - order[i + 1].direction == 2) {
                // Swap
                val temp = order[i]
                order[i] = order[i + 1]
                order[i + 1] = temp
                i += 2
            } else {
                i++
            }
        }
        time -= 1
    }
    var answer = ""
    order.forEach {
        answer += it.value
    }
    print(answer)
}