package src.boj.october.Problem2869

import kotlin.math.ceil

fun main() = with(System.`in`.bufferedReader()) {
    val (a, b, v) = readLine().split(" ").map { it.toInt() }
    print(ceil((v - a).toDouble() / (a - b)).toInt() + 1)
}