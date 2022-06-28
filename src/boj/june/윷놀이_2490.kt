package boj.june

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    for (i in 0 until 3) {
        val yut = readLine()!!.split(' ').map { it.toInt() }
        when (yut.sum()) {
            3 -> println("A")
            2 -> println("B")
            1 -> println("C")
            0 -> println("D")
            else -> println("E")
        }
    }
}
