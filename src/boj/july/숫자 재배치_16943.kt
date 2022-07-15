package boj.july

import java.io.BufferedReader
import java.io.InputStreamReader

private var B = 0
private var MAX = Int.MIN_VALUE
fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val input = readLine().split(' ')
    val numbers = input[0].map { it.toString() }
    val visited = BooleanArray(input[0].length)
    B = input[1].toInt()
    combination(numbers, visited, 0, "")
    if (MAX == Int.MIN_VALUE) {
        println(-1)
    } else {
        println(MAX)
    }
}

private fun combination(numbers: List<String>, visited: BooleanArray, depth: Int, s: String) {
    if (depth == numbers.size) {
        if (s.toInt() < B && !s.startsWith("0")) {
            MAX = MAX.coerceAtLeast(s.toInt())
        }
        return
    } else {
        for (i in numbers.indices) {
            if (!visited[i]) {
                visited[i] = true
                combination(numbers, visited, depth + 1, s + numbers[i])
                visited[i] = false
            }
        }
    }
}
