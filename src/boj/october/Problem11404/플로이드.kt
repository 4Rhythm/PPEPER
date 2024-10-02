package src.boj.october.Problem11404

private const val INF = 10000001
private lateinit var dist: Array<IntArray>
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val m = readLine().toInt()
    dist = Array(n + 1) { IntArray(n + 1) { INF } }.apply {
        for (i in 1..n) {
            this[i][i] = 0
        }
    }
    repeat(m) {
        val (a, b, c) = readLine().split(" ").map { it.toInt() }
        if (c < dist[a][b]) dist[a][b] = c
    }
    for (k in 1..n) {
        for (i in 1..n) {
            for (j in 1..n) {
                dist[i][j] = dist[i][j].coerceAtMost(dist[i][k] + dist[k][j])
            }
        }
    }
    dist.takeLast(n)
        .forEach {
            println(
                it.takeLast(n)
                    .map { if (it == INF) 0 else it }
                    .joinToString(" ")
            )
        }
}