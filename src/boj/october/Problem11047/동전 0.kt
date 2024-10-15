package src.boj.october.Problem11047

fun main() = with(System.`in`.bufferedReader()) {
    val (n, k) = readLine().split(" ").map { it.toInt() }
    val coinList = List(n) { readLine().toInt() }.sortedDescending()
    var target = k
    var count = 0
    for (coin in coinList) {
        if (target == 0) break

        if (coin <= target) {
            count += target / coin
            target %= coin
        }
    }
    println(count)
}