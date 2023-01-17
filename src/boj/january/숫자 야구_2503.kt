package boj.january

private lateinit var userInputList: Array<Triple<Int, Int, Int>>
fun main() = with(System.`in`.bufferedReader()) {
    var count = 0
    val N = readLine().toInt()
    userInputList = Array(N) {
        val (guess, strike, ball) = readLine().split(" ").map { it.toInt() }
        Triple(guess, strike, ball)
    }
    // 가능한 번호들 저장
    for (number in 123..987) {
        if (!checkDuplicate(number.toString())) continue
        var check = true
        // guess 한 값을 다 통과 하는 지 확인
        for (userInput in userInputList) {
            val (strike, ball) = calculateStrikeAndBall(number.toString(), userInput.first.toString())
            if (strike != userInput.second || ball != userInput.third) {
                check = false
                break
            }
        }
        if (check) count++
    }
    println(count)
}

private fun checkDuplicate(number: String): Boolean {
    if (number.contains("0")) return false
    for (i in number.indices) {
        if (1 < number.count { it == number[i] }) return false
    }
    return true
}

private fun calculateStrikeAndBall(number: String, guess: String): Pair<Int, Int> {
    var strike = 0
    var ball = 0
    for (i in number.indices) {
        if (number[i] == guess[i]) {
            strike++
        } else if (number.contains(guess[i])) {
            ball++
        }
    }
    return Pair(strike, ball)
}