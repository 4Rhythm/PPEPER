package boj.june

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val list = readLine()!!.split(' ').map { it.toInt() }
    var time = 0
    var position = 0
    for (i in 0 until list[0]) {
        val light = readLine()!!.split(' ').map { it.toInt() }
        // 신호등에 도달함
        time += (light[0] - position)
        // 현재 위치
        position = light[0]
        // 지나갈 수 있는지 확인
        val remain = time % (light[1] + light[2])
        if (remain < light[1]) {
            // 빨간불 시간보다 적음 -> 아직 빨간불
            time += (light[1] - remain)
        }
    }
    // 신호등을 다지나고 도로 끝까지 이동
    print(time + list[1] - position)
}
