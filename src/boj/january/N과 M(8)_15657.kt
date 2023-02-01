import java.lang.StringBuilder

private val answer = StringBuilder()
private lateinit var list: List<Int>
private var M = 0
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    list = readLine().split(" ").map { it.toInt() }.sorted()
    M = m
    backtracking8(0, 0, "")
    println(answer)
}

private fun backtracking8(start: Int, count: Int, result: String) {
    if (count == M) {
        answer.append(result).append("\n")
        return
    }
    for (i in start until list.size) {
        backtracking8(i,count + 1, "$result${list[i]} ")
    }
}