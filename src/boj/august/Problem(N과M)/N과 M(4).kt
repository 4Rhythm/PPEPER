package src.boj.august.`Problem(N과M)`

private lateinit var pick: IntArray
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    pick = IntArray(m)
    backtracking4(0, 1, n, m)
}

private fun backtracking4(idx: Int, start: Int, n: Int, m: Int) {
    if (idx == m) {
        println(pick.joinToString(" "))
        return
    }
    for (i in start..n) {
        pick[idx] = i
        backtracking4(idx + 1, i, n, m)
    }
}
