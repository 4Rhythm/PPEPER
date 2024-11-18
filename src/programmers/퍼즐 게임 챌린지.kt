package src.programmers

class `퍼즐 게임 챌린지` {
    private lateinit var diffsArray: IntArray
    private lateinit var timesArray: IntArray

    fun solution(diffs: IntArray, times: IntArray, limit: Long): Int {
        initialize(diffs, times)
        return findMinimumLevel(limit)
    }

    private fun findMinimumLevel(limit: Long): Int {
        var left = 1
        var right = diffsArray.max()

        while (left <= right) {
            val mid = (left + right) / 2

            if (isOverLimit(limit, mid)) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
        return right + 1
    }

    private fun isOverLimit(limit: Long, level: Int): Boolean {
        var time = 0L
        for (idx in diffsArray.indices) {
            val timeCurr = timesArray[idx]
            val diff = diffsArray[idx]
            if (level < diff) {
                val wrong = diff - level
                val timePrev = timesArray[idx - 1]
                time += wrong * (timeCurr + timePrev)
            }
            time += timeCurr
            if (limit < time) return true
        }
        return false
    }

    private fun initialize(diffs: IntArray, times: IntArray) {
        diffsArray = diffs
        timesArray = times
    }
}

fun main() {
    val s = `퍼즐 게임 챌린지`()
    val diffs = intArrayOf(1, 99999, 100000, 99995)
    val times = intArrayOf(9999, 9001, 9999, 9001)
    val limit = 3456789012L
    println(s.solution(diffs, times, limit))
}