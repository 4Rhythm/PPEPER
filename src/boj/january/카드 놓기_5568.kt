package boj.january

private lateinit var visited: BooleanArray
private lateinit var cards: List<Int>
private lateinit var answer: HashSet<String>
fun main() = with(System.`in`.bufferedReader()) {
    answer = HashSet()
    val n = readLine().toInt()
    val k = readLine().toInt()
    cards = List(n) { readLine().toInt() }
    visited = BooleanArray(n)
    choseCard(0, k, "")
    println(answer.size)
}

private fun choseCard(count: Int, depth: Int, number: String) {
    if (count == depth) {
        answer.add(number)
        return
    }
    for (i in cards.indices) {
        if (!visited[i]) {
            visited[i] = true
            choseCard(count + 1, depth, number + cards[i])
            visited[i] = false
        }
    }
}
