package src.boj.october.Problem9012

fun main() = with(System.`in`.bufferedReader()) {
    repeat(readLine().toInt()) {
        var count = 0
        readLine().forEach {
            if (count < 0) return@forEach
            if (it == '(') count++
            else count--
        }
        if (count == 0) println("YES") else println("NO")
    }
}