package src.boj.august.`Problem(N과M)`

private lateinit var number: List<Int>
private lateinit var pick: IntArray
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    number = readLine().split(" ").map { it.toInt() }.sorted()
    pick = IntArray(m)
    backtracking8(0, 0, m)

}

private fun backtracking8(idx: Int, start: Int, m: Int) {
    if (idx == m) {
        println(pick.joinToString(" "))
        return
    }
    for (i in start until number.size) {
        pick[idx] = number[i]
        backtracking8(idx + 1, i, m)
    }
}
