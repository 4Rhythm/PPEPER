package src.boj.september.Problem17490

private lateinit var cost: List<Int>
private lateinit var noBridge: HashSet<String>
private lateinit var parent: IntArray
fun main() = with(System.`in`.bufferedReader()) {
    val input = readLine().split(" ")
    val n = input[0].toInt()
    val m = input[1].toInt()
    val k = input[2].toLong()
    var sum = 0L
    var count = 0
    parent = IntArray(n + 1) { it }
    cost = listOf(0) + readLine().split(" ").map { it.toInt() }
    noBridge = HashSet()
    for (i in 1..n) {
        val to = i % n
        if (to == 0) {
            noBridge.add("${to + 1}-$i")
        } else {
            noBridge.add("$i-${i + 1}")
        }
    }
    repeat(m) {
        val (a, b) = readLine().split(" ").map { it.toInt() }
        val small = a.coerceAtMost(b)
        val large = a.coerceAtLeast(b)
        noBridge.remove("$small-$large")
    }
    noBridge.forEach { line ->
        val (a, b) = line.split("-").map { it.toInt() }
        if (getParent(a) != getParent(b)) {
            union(a, b)
        }
    }
    for (i in 1..n) {
        if (i == getParent(i)) {
            sum += cost[i]
            count++
        }
    }
    if (sum <= k || count <= 1) println("YES") else println("NO")
}

private fun union(a: Int, b: Int) {
    val pa = getParent(a)
    val pb = getParent(b)
    if (cost[pa] < cost[pb]) {
        parent[pb] = pa
    } else {
        parent[pa] = pb
    }
}

private fun getParent(node: Int): Int {
    if (parent[node] != node) {
        parent[node] = getParent(parent[node])
    }
    return parent[node]
}