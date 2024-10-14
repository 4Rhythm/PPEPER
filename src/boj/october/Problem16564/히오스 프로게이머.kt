package src.boj.october.Problem16564

private lateinit var list: List<Int>
private var K = 0
fun main() = with(System.`in`.bufferedReader()) {
    val (n, k) = readLine().split(" ").map { it.toInt() }
    K = k
    list = List(n) { readLine().toInt() }.sorted()
    var left = list.first()
    var right = list.last()
    while (left <= right) {
        val mid = (left + right) / 2
        if (checkLevel(mid)) {
            left = mid + 1
        } else {
            right = mid - 1
        }
    }
    println(right)
}

private fun checkLevel(mid: Int): Boolean {
    var sum = 0L
    for (level in list) {
        if (mid <= level) break
        sum += mid - level
    }
    return sum <= K
}
