package src.boj.september.Problem13305

private lateinit var length: List<Int>
private lateinit var area: List<Int>
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    length = readLine().split(" ").map { it.toInt() }
    area = readLine().split(" ").map { it.toInt() }
    var current = area.first()
    var answer = 0L
    for (i in 0 until area.size - 1) {
        current = current.coerceAtMost(area[i])
        answer += current * length[i].toLong()
    }
    println(answer)
}