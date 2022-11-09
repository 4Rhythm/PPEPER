package boj.november

import java.lang.StringBuilder

fun main() {
    val (N, M) = readLine()!!.split(' ').map { it.toInt() }
    val dna = Array(N) {
        mutableListOf<Char>().apply {
            val input = readLine()!!
            input.forEach { c ->
                add(c)
            }
        }
    }
    // DNA 구조
    val resultDna = StringBuilder()
    var count = 0
    // 각 위치의 dna 물질이 많은 것을 선택하면 가장 차이가 적어진다
    for (i in 0 until M) {
        val frequency = IntArray(4) { 0 }
        // dna 확인
        for (j in dna.indices) {
            when (dna[j][i]) {
                'A' -> frequency[0]++
                'C' -> frequency[1]++
                'G' -> frequency[2]++
                else -> frequency[3]++
            }
        }
        val max = frequency.maxOf { it }
        val index = frequency.indexOf(max)
        resultDna.append(
            when (index) {
                0 -> 'A'
                1 -> 'C'
                2 -> 'G'
                else -> 'T'
            }
        )
    }
    for (i in 0 until M) {
        for (j in dna.indices) {
            if (resultDna[i] != dna[j][i]) {
                count++
            }
        }
    }
    println(resultDna)
    println(count)
}