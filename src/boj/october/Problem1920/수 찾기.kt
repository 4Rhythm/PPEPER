package src.boj.october.Problem1920

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val set = readLine().split(" ").map { it.toInt() }.toSet()
    val m = readLine().toInt()
    readLine().split(" ").map { it.toInt() }
        .map {
            if (set.contains(it)) 1 else 0
        }
        .forEach(::println)
}