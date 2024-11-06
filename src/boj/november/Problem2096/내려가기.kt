package src.boj.november.Problem2096

private lateinit var list: Array<List<Int>>
private lateinit var minDp: Array<IntArray>
private lateinit var maxDp: Array<IntArray>
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    list = Array(n) { readLine().split(" ").map { it.toInt() } }
    minDp = Array(n) { IntArray(3) { Int.MAX_VALUE } }
    maxDp = Array(n) { IntArray(3) { -1 } }
    val max = searchMax(n - 1, 0).coerceAtLeast(searchMax(n - 1, 1)).coerceAtLeast(searchMax(n - 1, 2))
    val min = searchMin(n - 1, 0).coerceAtMost(searchMin(n - 1, 1)).coerceAtMost(searchMin(n - 1, 2))
    println("$max $min")
}

private fun searchMin(n: Int, i: Int): Int {
    if (n < 0) return 0
    if (minDp[n][i] != Int.MAX_VALUE) return minDp[n][i]
    when (i) {
        0 -> {
            minDp[n][i] = list[n][i] + searchMin(n - 1, i).coerceAtMost(searchMin(n - 1, 1))
        }
        1 -> {
            minDp[n][i] = list[n][i] + searchMin(n - 1, 0).coerceAtMost(searchMin(n - 1, 2)).coerceAtMost(searchMin(n - 1, 1))
        }
        2 -> {
            minDp[n][i] = list[n][i] + searchMin(n - 1, 2).coerceAtMost(searchMin(n - 1, 1))
        }
    }
    return minDp[n][i]
}

private fun searchMax(n: Int, i: Int): Int {
    if (n < 0) return 0
    if (maxDp[n][i] != -1) return maxDp[n][i]
    when (i) {
        0 -> {
            maxDp[n][i] = list[n][i] + searchMax(n - 1, i).coerceAtLeast(searchMax(n - 1, 1))
        }
        1 -> {
            maxDp[n][i] = list[n][i] + searchMax(n - 1, 0).coerceAtLeast(searchMax(n - 1, 2)).coerceAtLeast(searchMax(n - 1, 1))
        }
        2 -> {
            maxDp[n][i] = list[n][i] + searchMax(n - 1, i).coerceAtLeast(searchMax(n - 1, 1))
        }
    }
    return maxDp[n][i]
}
