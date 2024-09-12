package src.boj.september.Problem2285

private lateinit var list: List<List<Long>>
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    list = List(n) { readLine().split(" ").map { it.toLong() }}.sortedBy { it[0] }
    val max = (list.sumOf { it[1] } + 1) / 2
    var count = 0L
    for ((x, people) in list) {
        count += people
        if (max <= count) {
            println(x)
            break
        }
    }
}