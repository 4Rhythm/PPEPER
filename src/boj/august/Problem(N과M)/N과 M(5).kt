package src.boj.august.`Problem(N과M)`

private lateinit var pick: IntArray
private lateinit var number: List<Int>
private lateinit var visited: BooleanArray
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    pick = IntArray(m)
    number = readLine().split(" ").map { it.toInt() }.sorted()
    visited = BooleanArray(n)
    backtracking5(0, m)
}

private fun backtracking5(idx: Int, m: Int) {
    if (idx == m) {
        println(pick.joinToString(" "))
        return
    }
    for (i in number.indices) {
        if (!visited[i]) {
            visited[i] = true
            pick[idx] = number[i]
            backtracking5(idx + 1, m)
            visited[i] = false
        }
    }
}
