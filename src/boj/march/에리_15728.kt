package boj.march

private lateinit var listA: List<Int>
private lateinit var listB: List<Int>
private lateinit var visited: BooleanArray
fun main() = with(System.`in`.bufferedReader()) {
    val (n, k) = readLine().split(" ").map { it.toInt() }
    listA = readLine().split(" ").map { it.toInt() }
    listB = readLine().split(" ").map { it.toInt() }.sorted()
    visited = BooleanArray(n)
    // k장 견재
    for (i in 0 until k) {
        removeCard()
    }
    // 가장 큰 값 계산
    var answer = Integer.MIN_VALUE
    for (a in listA) {
        for (j in listB.indices) {
            if (!visited[j]) {
                answer = answer.coerceAtLeast(a * listB[j])
            }
        }
    }
    println(answer)
}

private fun removeCard() {
    var max = Integer.MIN_VALUE
    var idx = 0
    for (i in listA.indices) {
        val a = listA[i]
        for (j in listB.indices) {
            if (!visited[j]) {
                if (max < a * listB[j]) {
                    max = a * listB[j]
                    idx = j
                }
            }
        }
    }
    visited[idx] = true
}
