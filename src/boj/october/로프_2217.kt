package boj.october

fun main() {
    val N = readln().toInt()
    val rope = mutableListOf<Int>().apply {
        repeat(N) {
            add(readln().toInt())
        }
    }.sorted()
    var maxWeight = rope[rope.size - 1]
    for (i in rope.indices) {
        maxWeight = maxWeight.coerceAtLeast((rope.count() - i) * rope[i])
    }
    println(maxWeight)
}