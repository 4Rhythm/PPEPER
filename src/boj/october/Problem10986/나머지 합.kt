package src.boj.october.Problem10986

private lateinit var sum: LongArray
private lateinit var remain: LongArray
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val number = readLine().split(" ").map { it.toLong() }
    sum = LongArray(n + 1)
    remain = LongArray(n)
    for (i in 1..n) {
        sum[i] = (sum[i - 1] + number[i - 1]) % m
        remain[sum[i].toInt()]++
    }
    var count = remain[0]
    for (i in 0 until m) {
        count += (remain[i] * (remain[i] - 1)) / 2
    }
    println(count)
}
