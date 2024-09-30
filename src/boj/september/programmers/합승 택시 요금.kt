package src.boj.september.programmers

private const val INF = 3000000
class `합승 택시 요금` {
    private lateinit var distance: Array<IntArray>

    fun solution(n: Int, s: Int, a: Int, b: Int, fares: Array<IntArray>): Int {
        var answer = INF
        distance = Array(n + 1) { IntArray(n + 1) { INF } }.apply {
            for (i in 1..n) {
                this[i][i] = 0
            }
        }
        initDistance(fares)
        floyd(n)
        for (i in 1..n) {
            answer = answer.coerceAtMost(distance[s][i] + distance[i][a] + distance[i][b])
        }
        return answer
    }

    private fun floyd(n: Int) {
        for (k in 1..n) {
            for (i in 1..n) {
                for (j in 1..n) {
                    distance[i][j] = distance[i][j].coerceAtMost(distance[i][k] + distance[k][j])
                }
            }
        }
    }

    private fun initDistance(fares: Array<IntArray>) {
        fares.forEach { (from, to, cost) ->
            distance[from][to] = cost
            distance[to][from] = cost
        }
    }
}

fun main() {
    val s = `합승 택시 요금`()
    val fares = arrayOf(
        intArrayOf(5, 7, 9),
        intArrayOf(4, 6, 4),
        intArrayOf(3, 6, 1),
        intArrayOf(3, 2, 3),
        intArrayOf(2, 1, 6),
    )
    println(s.solution(7, 3, 4, 1, fares))
}