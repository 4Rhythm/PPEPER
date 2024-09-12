package src.boj.september

import kotlin.math.sqrt

private lateinit var isPrime: BooleanArray
fun main() = with(System.`in`.bufferedReader()) {
    readLine()
    val numbers = readLine().split(" ").map { it.toInt() }
    val maxNumber = numbers.max()
    isPrime = BooleanArray(maxNumber + 1) { true }.apply {
        this[0] = false
        this[1] = false
    }
    for (i in 2..sqrt(maxNumber.toFloat()).toInt()) {
        if (isPrime[i]) {
            for (j in i * i..maxNumber step i) {
                isPrime[j] = false
            }
        }
    }
    println(numbers.count { isPrime[it] })
}