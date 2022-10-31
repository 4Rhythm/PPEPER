package boj.november

fun main() {
    val list = mutableListOf<Int>().apply {
        while (true) {
            val (L, P, V) = readLine()!!.split(' ').map { it.toInt() }
            if (L == 0) {
                break
            }
            var useCampingDays = 0
            // 연속되는 일의 수
            val count = V / P
            // 남은 휴가의 수
            val remainDays = V % P
            useCampingDays += count * L
            useCampingDays += if (remainDays < L) {
                remainDays
            } else {
                L
            }
            add(useCampingDays)
            // 남은 일수에서 사용할 수 있는 캠핑날짜 확인
        }
    }
    for (i in list.indices) {
        println("Case ${i + 1}: ${list[i]}")
    }
}