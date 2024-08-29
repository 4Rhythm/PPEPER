package src.boj.august.`Problem(N과M)`

private lateinit var pick: IntArray
private lateinit var visited: BooleanArray
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    pick = IntArray(m)
    visited = BooleanArray(n)
    backtracking1(0, n, m)
}

private fun backtracking1(idx: Int, n: Int, r: Int) {
    if (idx == r) {
        println(pick.joinToString(" "))
        return
    }
    for (i in 0 until n) {
        if (!visited[i]) {
            visited[i] = true
            pick[idx] = i + 1
            backtracking1(idx + 1, n, r)
            visited[i] = false
        }
    }
}
