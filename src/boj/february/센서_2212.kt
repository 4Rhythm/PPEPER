package boj.february

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val k = readLine().toInt()
    val sensor = readLine().split(" ").map { it.toInt() }.sorted().toIntArray()
    (1 until n).forEach { i ->
        sensor[i - 1] = sensor[i] - sensor[i - 1]
    }
    sensor.sort()
    if (n <= k) println(0)
    else {
        println(sensor.take(n - k).sum())
    }
}