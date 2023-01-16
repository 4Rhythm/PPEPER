package boj.january

private var answer = 0
private var N = 0
fun main() = with(System.`in`.bufferedReader()) {
    val (n, K) = readLine().split(" ").map { it.toInt() }
    answer = 0
    N = n
    val numbers = readLine().split(" ").map { it.toInt() }.sortedDescending()
    getMaxNumber(numbers, 0)
    println(answer)
}

private fun getMaxNumber(numbers: List<Int>, temp: Int) {
    if (N < temp) return
    if (answer < temp) answer = temp
    for (number in numbers) {
        getMaxNumber(numbers, temp * 10 + number)
    }
}
