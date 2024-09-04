package src.boj.september.Problem1174

private lateinit var numbers: ArrayList<Long>
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    numbers = ArrayList()
    for (i in 0..9) {
        makeNumber(i.toLong())
    }
    numbers.sort()
    val answer = numbers.getOrNull(n - 1) ?: -1
    println(answer)
}

private fun makeNumber(i: Long) {
    numbers.add(i)
    val last = i % 10
    if (last <= 0) return
    for (j in last - 1 downTo 0) {
        makeNumber(i * 10 + j)
    }
}
