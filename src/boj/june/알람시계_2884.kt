package boj.june

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val time = readLine()!!.split(' ').map { it.toInt() }.toMutableList()
    // 늦잠
    time[0] = if (time[1] - 45 < 0) time[0] - 1 else time[0]
    time[1] = if (time[1] - 45 < 0) time[1] + 15 else time[1] - 45
    if (time[0] < 0) time[0] = 23
    print("${time[0]} ${time[1]}")
}
