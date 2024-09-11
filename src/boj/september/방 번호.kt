package src.boj.september

import kotlin.math.ceil

fun main() = with(System.`in`.bufferedReader()) {
    println(
        readLine()
            .map { if (it == '6') '9' else it }
            .groupingBy { it }
            .eachCount()
            .mapValues { if (it.key == '9') ceil(it.value / 2f).toInt() else it.value }
            .maxBy { it.value }
            .value
    )
}