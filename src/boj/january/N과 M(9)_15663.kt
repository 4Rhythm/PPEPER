package boj.january

private val answer = ArrayList<String>()
private lateinit var visited: BooleanArray
private lateinit var list: List<Int>
private var M = 0
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    list = readLine().split(" ").map { it.toInt() }.sorted()
    M = m
    visited = BooleanArray(n + 1)
    backtracking9(0, "")
    answer.distinct().forEach {
        println(it)
    }
}

private fun backtracking9( count: Int, result: String) {
    if (count == M) {
        answer.add(result)
        return
    }
    for (i in list.indices) {
        if (!visited[i]) {
            visited[i] = true
            backtracking9(count + 1, "$result${list[i]} ")
            visited[i] = false
        }
    }
}
