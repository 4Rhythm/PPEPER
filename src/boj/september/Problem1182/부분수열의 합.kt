package src.boj.september.Problem1182

private lateinit var list: List<Int>
private var target = 0
private var answer = 0
fun main() = with(System.`in`.bufferedReader()) {
    val (n, s) = readLine().split(" ").map { it.toInt() }
    target = s
    list = readLine().split(" ").map { it.toInt() }
    pickNumber(0, 0)
    if (target == 0) println(answer - 1) else println(answer)
}

private fun pickNumber(idx: Int, result: Int) {
    if (idx == list.size) {
        if (result == target) answer++
        return
    }
    pickNumber(idx + 1, result)
    pickNumber(idx + 1, result + list[idx])
}
