package src.boj.august.`Problem(N과M)`

private lateinit var pick: IntArray
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    pick = IntArray(m)
    backtracking2(1, 0, n, m)
}

private fun backtracking2(start: Int, idx: Int, n: Int, m: Int) {
    if (idx == m) {
        println(pick.joinToString(" "))
        return
    }
    for (i in start..n) {
        pick[idx] = i
        backtracking2(i + 1,idx + 1, n, m)
    }
}
