package src.boj.october.Problem1620

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val pokeString = List(n) { readLine() }
    val pokeMap = pokeString.mapIndexed { index, s -> s to index + 1}.toMap()
    repeat(m) {
        val input = readLine()
        if (input[0] in ('0'..'9')) println(pokeString[input.toInt() - 1]) else println(pokeMap[input])
    }
}