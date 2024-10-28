package src.boj.october.Problem11727

private lateinit var count: IntArray
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    count = IntArray(n + 1) { -1 }
    println(search(n))
}

private fun search(n: Int): Int {
    if (n <= 1) return 1
    if (count[n] != -1) return count[n]
    count[n] = (search(n - 1) + 2 * search(n - 2)) % 10007
    return count[n]
}