package src.boj.september

fun main() = with(System.`in`.bufferedReader()) {
    readLine().toInt().run {
        if ((this % 4 == 0 && this % 100 != 0) || this % 400 == 0) {
            println(1)
        } else {
            println(0)
        }
    }
}