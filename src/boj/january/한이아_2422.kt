package boj.january

private val list = ArrayList<String>()
private lateinit var numbers: IntArray
fun main() = with(System.`in`.bufferedReader()) {
    var count = 0
    val (N, M) = readLine().split(" ").map { it.toInt() }
    val checked = Array(N + 1) { BooleanArray(N + 1) }
    repeat(M) {
        val (x, y) = readLine().split(" ").map { it.toInt() }
        checked[x][y] = true
        checked[y][x] = true
    }
    for (i in 1 until checked.size) {
        for (j in i + 1 until checked.size) {
            // 섞어 먹을 수 있음
            if (!checked[i][j]) {
                for (k in j + 1 until checked.size) {
                    if (!checked[i][k] && !checked[j][k]) {
                        count++
                    }
                }
            }
        }
    }
    println(count)
}