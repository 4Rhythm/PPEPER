package boj.march

import kotlin.math.abs

private val visited = BooleanArray(1002)
private var min = Int.MAX_VALUE
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    if (m != 0) {
        val list = readLine().split(" ").map { it.toInt() }.toSet()
        list.forEach {
            visited[it] = true
        }
    }
    for (i in 1..1001) {
        if (visited[i]) continue
        for(j in 1..1001) {
            if (visited[j]) continue
            for (k in 1..1001) {
                if (visited[k]) continue
                min = min.coerceAtMost(abs(n - i * j * k))
            }
        }
    }
    println(min)
}
