package src.boj.september

fun main() = with(System.`in`.bufferedReader()) {
    List(readLine().toInt()) { readLine() }
        .distinct()
        .sortedWith(compareBy<String> { it.length }.thenBy { it })
        .forEach(::println)
}