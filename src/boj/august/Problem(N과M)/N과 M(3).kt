package src.boj.august.`Problem(N과M)`

private val answer = StringBuilder()
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    backtracking3(0, n, m,"")
    println(answer)
}

private fun backtracking3(idx: Int, n: Int, m: Int, result: String) {
    if (idx == m) {
        answer.append(result).append("\n")
        return
    }
    for (i in 1..n) {
        backtracking3(idx + 1, n, m, "$result$i ")
    }
}
