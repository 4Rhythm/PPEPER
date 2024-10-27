package src.boj.october.Problem1904

private lateinit var count: IntArray
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    count = IntArray(n + 1) { 0 }.apply {
        this[0] = 1
        this[1] = 1
    }
    for (i in 2..n) {
        count[i] = (count[i - 1] + count[i - 2]) % 15746
    }
    println(count[n])
}