package src.boj.september

fun main() = with(System.`in`.bufferedReader()) {
    readLine().let { input ->
        println(
            ('a'..'z')
                .map { alpha -> input.count { alpha == it } }
                .joinToString(" ")
        )
    }
}
