package boj.october

import java.util.*

fun main() {
    val classSize = readln().toInt()
    val classList = mutableListOf<Pair<Int, Int>>()
    val roomList = PriorityQueue<Int>()
    repeat(classSize) {
        val (startTime, endTime) = readLine()!!.split(' ').map { it.toInt() }
        classList.add(Pair(startTime, endTime))
    }
    // 강의의 시작시간이 빠른기준으로 끝나는 시간이 빠른 기준으로 정렬
    classList.sortWith(
        compareBy<Pair<Int, Int>> { it.first }.thenBy { it.second }
    )
    // 최소한의 강의실로 모든 수업을 배정
    possibleClasses(classList, roomList)
    // 최소의 강의실의 개수 출력
    println(roomList.size)
}

private fun possibleClasses(classList: MutableList<Pair<Int, Int>>, roomList: PriorityQueue<Int>) {
    for (currentClass in classList) {
        // 강의의 끝나는 시간이 다음 강의의 시작시간 보다 작으면 들을 수 있다
        if (roomList.isNotEmpty() && roomList.peek() <= currentClass.first) {
            roomList.poll()
        }
        roomList.offer(currentClass.second)
    }
}
