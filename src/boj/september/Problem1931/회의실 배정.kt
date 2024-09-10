package src.boj.september.Problem1931

private lateinit var list: List<List<Int>>
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    list = List(n) { readLine().split(" ").map { it.toInt() }}
        .sortedWith(compareBy<List<Int>> { it[1] }.thenBy { it[0] })
    var count = 1
    var endTime = list[0][1]
    for (i in 1 until list.size) {
        if (endTime <= list[i][0]) {
            endTime = list[i][1]
            count += 1
        }
    }
    println(count)
}