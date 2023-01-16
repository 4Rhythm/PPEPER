package boj.january

private var satisfy = 0
private var N = 0
private lateinit var check: BooleanArray
private lateinit var preferences: Array<List<Int>>
fun main() = with(System.`in`.bufferedReader()) {
    val (n, M) = readLine().split(" ").map { it.toInt() }
    N = n
    check = BooleanArray(M)
    preferences = Array(N) {
        readLine().split(" ").map { it.toInt() }
    }
    choseChicken(0, 3)
    println(satisfy)
}

private fun choseChicken(start: Int, count: Int) {
    if (count == 0) {
        satisfy = satisfy.coerceAtLeast(calculate())
        return
    }
    for (i in start until check.size) {
        check[i] = true
        choseChicken(i + 1, count - 1)
        check[i] = false
    }
}

private fun calculate(): Int {
    var sum = 0
    for (i in 0 until N) {
        var max = 0
        val list = preferences[i]
        for (j in list.indices) {
            if (check[j]) {
               max = max.coerceAtLeast(list[j])
            }
        }
        sum += max
    }
    return sum
}
