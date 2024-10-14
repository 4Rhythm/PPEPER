package src.boj.october.Problem2839

fun main() = with(System.`in`.bufferedReader()) {
    var n = readLine().toInt()
    var count = 0
    while (true) {
        if (n % 5 == 0) {
            println("${n / 5 + count}")
            break
        }
        if (n - 3 < 0) {
            println(-1)
            break
        }
        n -= 3
        count += 1
    }
}