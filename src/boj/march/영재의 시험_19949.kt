package boj.march

private val numbers = listOf(1, 2, 3, 4, 5)
private lateinit var answer: List<Int>
private val pick = IntArray(10)
private var count = 0
fun main() {
    answer = readLine()!!.split(" ").map { it.toInt() }
    makePossibleAnswer(-1, 0, 0)
    println(count)
}

private fun makePossibleAnswer(prev: Int, count: Int, r: Int) {
    if (r == 10) {
        checkAnswer()
        return
    }
    for (i in numbers.indices) {
        // 2번연속이면 다음엔 못뽑는다.
        if (count == 1 && i == prev) continue
        // 전에 있는걸 뽑음
        if (i == prev) {
            pick[r] = numbers[i]
            makePossibleAnswer(i, count + 1, r + 1)
        } else {
            pick[r] = numbers[i]
            makePossibleAnswer(i, 0, r + 1)
        }
    }
}

private fun checkAnswer() {
    var correct = 0
    for (i in answer.indices) {
        if (answer[i] == pick[i]) correct++
        if (correct == 5) break
    }
    if (correct == 5) count += 1
}
