package programmers

import java.util.*

data class Print(
    var position: Int,
    var priority: Int
)
class Printer {
    fun solution(priorities: IntArray, location: Int): Int {
        val list = priorities.mapIndexed { index, i ->
            Print(index, i)
        }
        val printerQueue = LinkedList(list)
        var count = 0
        // 뽑아야하는 location 위치
        while (printerQueue.isNotEmpty()) {
            val print = printerQueue.poll()
            // 우선순위 확인
            if (check(printerQueue, print)) {
                printerQueue.offer(print)
            } else {
                if (print.position == location) {
                    return count + 1
                } else {
                    count++
                }
            }
        }
        return 0
    }
}

private fun check(list: LinkedList<Print>, print: Print): Boolean {
    for (value in list) {
        if (print.priority < value.priority) {
            return true
        }
    }
    return false
}