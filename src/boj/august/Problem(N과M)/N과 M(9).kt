package src.boj.august.`Problem(N과M)`

private lateinit var number: List<Int>
private lateinit var answer: LinkedHashSet<String>
private lateinit var visited: BooleanArray
private lateinit var pick: IntArray
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    number = readLine().split(" ").map { it.toInt() }.sorted()
    pick = IntArray(m)
    visited = BooleanArray(n)
    answer = LinkedHashSet()
    backtracking9(0, m)
    answer.forEach {
        println(it)
    }
}

private fun backtracking9(idx: Int, m: Int) {
    if (idx == m) {
        answer.add(pick.joinToString(" "))
        return
    }
    for (i in number.indices) {
        if (!visited[i]) {
            visited[i] = true
            pick[idx] = number[i]
            backtracking9(idx + 1, m)
            visited[i] = false
        }
    }
}
