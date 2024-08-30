package src.boj.august.`Problem(N과M)`

private lateinit var number: List<Int>
private lateinit var pick: IntArray
private lateinit var answer: LinkedHashSet<String>
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    number = readLine().split(" ").map { it.toInt() }.sorted()
    pick = IntArray(m)
    answer = LinkedHashSet()
    backtracking10(0, 0, m)
    answer.forEach {
        println(it)
    }
}

private fun backtracking10(idx: Int, start: Int, m: Int) {
    if (idx == m) {
        answer.add(pick.joinToString(" "))
        return
    }
    for (i in start until number.size) {
        pick[idx] = number[i]
        backtracking10(idx + 1, i + 1, m)
    }
}
