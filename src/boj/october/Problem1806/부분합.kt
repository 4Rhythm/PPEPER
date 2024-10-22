package src.boj.october.Problem1806

private lateinit var list: IntArray
private var minLength = Int.MAX_VALUE
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    list = IntArray(n + 1)
    readLine().split(" ").map { it.toInt() }.forEachIndexed { index, value ->
        list[index + 1] = list[index] + value
    }
    var left = 0
    var right = 0
    while (right in left..n) {
        if (m <= list[right] - list[left]) {
            minLength = minLength.coerceAtMost(right - left)
            left += 1
        } else {
            right += 1
        }
    }
    if (minLength == Int.MAX_VALUE) println(0) else println(minLength)
}