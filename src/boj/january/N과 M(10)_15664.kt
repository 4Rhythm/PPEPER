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
    backtracking10(0, 0, "")
    answer.distinct().forEach {
        println(it)
    }
}

private fun backtracking10(start: Int, count: Int, result: String) {
    if (count == M) {
        answer.add(result)
        return
    }
    for (i in start until list.size) {
        if (!visited[i]) {
            visited[i] = true
            backtracking10(i + 1, count + 1, "$result${list[i]} ")
            visited[i] = false
        }
    }
}