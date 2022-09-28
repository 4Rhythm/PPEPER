package boj.september

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.abs

var answer = Int.MAX_VALUE
private val house = ArrayList<Pair<Int, Int>>()
private val chicken = ArrayList<Pair<Int, Int>>()
private lateinit var visited: BooleanArray
fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val input = readLine().split(' ').map { it.toInt() }
    val M = input[1]
    for (i in 0 until input[0]) {
        val value = readLine().split(' ').map { it.toInt() }
        for (j in value.indices) {
            if (value[j] == 1) {
                house.add(Pair(i, j))
            }
            if (value[j] == 2) {
                chicken.add(Pair(i, j))
            }
        }
    }
    visited = BooleanArray(chicken.size)
    selectChicken(0, M)
    println(answer)
}

private fun selectChicken(start: Int, M: Int) {
    if (M == 0) {
        answer = answer.coerceAtMost(shortDistance())
        return
    }
    for (i in start until chicken.size) {
        visited[i] = true
        selectChicken(i + 1, M - 1)
        visited[i] = false
    }
}

private fun shortDistance(): Int {
    var sum = 0
    for (h in house) {
        var value = Int.MAX_VALUE
        // 집마다 가장 짧은 치킨집을 더해준다
        for (i in visited.indices) {
            if (visited[i]) {
                value = value.coerceAtMost(getDistance(h, chicken[i]))
            }
        }
        sum += value
    }
    return sum
}

private fun getDistance(house: Pair<Int, Int>, chicken: Pair<Int, Int>): Int {
    return abs(house.first - chicken.first) + abs(house.second - chicken.second)
}
