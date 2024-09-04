package src.boj.september.Problem3980

private lateinit var list: Array<List<Int>>
private lateinit var pick: IntArray
private lateinit var visited: BooleanArray
private var buffer = 0
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val answer = StringBuilder()
    repeat(n) {
        buffer = 0
        list = Array(11) { readLine().split(" ").map { it.toInt() } }
        pick = IntArray(11)
        visited = BooleanArray(11)
        pickPlayers(0)
        answer.append("$buffer\n")
    }
    println(answer)
}

private fun pickPlayers(n: Int) {
    if (n == 11) {
        buffer = buffer.coerceAtLeast(pick.sum())
        return
    }
    for (i in list.indices) {
        if (visited[i]) continue
        if (list[i][n] == 0) continue
        visited[i] = true
        pick[n] = list[i][n]
        pickPlayers(n + 1)
        visited[i] = false
    }
}
