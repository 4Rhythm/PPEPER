package boj.february

private var answer = 0
fun main() = with(System.`in`.bufferedReader()) {
    val s = readLine()
    val target = readLine()
    canMakeWord(s, target)
    println(answer)
}

private fun canMakeWord(s: String, target: String) {
    if (s.length == target.length) {
        if (s == target) {
            answer = 1
        }
        return
    }
    if (target[target.length - 1] == 'A') {
        canMakeWord(s, target.substring(0, target.length - 1))
    }
    if (target[0] == 'B') {
        canMakeWord(s, target.substring(1).reversed())
    }
}
