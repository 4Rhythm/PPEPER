package src.boj.august.`Problem(N과M)`

private lateinit var number: List<Int>
private val answer = StringBuilder()
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    number = readLine().split(" ").map { it.toInt() }.sorted()
    backtracking7(0, m, "")
    println(answer)
}

private fun backtracking7(idx: Int, m: Int, result: String) {
    if (idx == m) {
        answer.append(result).append("\n")
        return
    }
    for (num in number) {
        backtracking7(idx + 1, m, "$result$num ")
    }
}
