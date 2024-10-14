package src.boj.october.Problem2609

fun main() = with(System.`in`.bufferedReader()) {
    val (a, b) = readLine().split(" ").map { it.toInt() }
    println(gcd(a, b))
    println(a * b / gcd(a, b))
}

private fun gcd(a: Int, b: Int): Int {
    return if (b != 0) gcd(b, a % b) else a
}
