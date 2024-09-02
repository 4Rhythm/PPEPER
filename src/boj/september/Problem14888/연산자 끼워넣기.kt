package src.boj.september.Problem14888

private lateinit var numbers: List<Int>
private lateinit var op: IntArray
private var minAnswer = Int.MAX_VALUE
private var maxAnswer = Int.MIN_VALUE
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    numbers = readLine().split(" ").map { it.toInt() }
    op = readLine().split(" ").map { it.toInt() }.toIntArray()
    calculate(numbers[0], 1)
    println("$maxAnswer\n$minAnswer")
}

private fun calculate(result: Int, idx: Int) {
    if (idx == numbers.size) {
        minAnswer = minAnswer.coerceAtMost(result)
        maxAnswer = maxAnswer.coerceAtLeast(result)
        return
    }
    for (i in op.indices) {
        if (op[i] != 0) {
            op[i]--
            when (i) {
                0 -> calculate(result + numbers[idx], idx + 1)
                1 -> calculate(result - numbers[idx], idx + 1)
                2 -> calculate(result * numbers[idx], idx + 1)
                3 -> calculate(result / numbers[idx], idx + 1)
            }
            op[i]++
        }
    }
}
