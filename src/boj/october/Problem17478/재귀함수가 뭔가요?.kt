package src.boj.october.Problem17478

private val output1 = listOf(
    "\"재귀함수가 뭔가요?\"",
    "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.",
    "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.",
    "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\""
)
private val output2 = "\"재귀함수는 자기 자신을 호출하는 함수라네\""
private val output3 = "라고 답변하였지."
private const val LINE = "____"
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.")
    recursion(0, n)
}

private fun recursion(line: Int, n: Int) {
    if (n == 0) {
        println(LINE.repeat(line) + output1.first())
        println(LINE.repeat(line) + output2)
        println(LINE.repeat(line) + output3)
        return
    }
    output1.forEach {
        println(LINE.repeat(line) + it)
    }
    recursion(line + 1, n - 1)
    println(LINE.repeat(line) + output3)
}
