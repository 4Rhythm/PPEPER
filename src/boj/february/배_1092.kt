package boj.february

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val crane = readLine().split(" ").map { it.toInt() }.sortedDescending().toMutableList()
    val m = readLine().toInt()
    val box = readLine().split(" ").map { it.toInt() }.sortedDescending().toMutableList()
    if (crane[0] < box[0]) {
        println(-1)
    } else {
        var answer = 0
        while (box.isNotEmpty()) {
            for (c in crane) {
                // 크래인에 박스를 담음
                for (i in box.indices) {
                    if (box[i] <= c) {
                        box.removeAt(i)
                        break
                    }
                }
            }
            answer++
        }
        println(answer)
    }
}