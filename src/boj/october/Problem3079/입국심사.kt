package src.boj.october.Problem3079

private lateinit var times: List<Int>
private var M = 0L
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    M = m.toLong()
    times = List(n) { readLine().toInt() }.sorted()
    search()
}

private fun search() {
    var left = 0L
    var right = M * times.last()

    while (left <= right) {
        val mid = (left + right) / 2
        if (count(mid)) {
            right = mid - 1
        } else {
            left = mid + 1
        }
    }
    println(right + 1)
}

private fun count(endTime: Long): Boolean {
    var check = 0L
    for (time in times) {
        check += (endTime / time)
        if (M <= check) return true
    }
    return false
}
