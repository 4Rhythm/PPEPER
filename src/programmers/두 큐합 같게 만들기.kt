package src.programmers

import java.util.LinkedList

class `두 큐합 같게 만들기` {
    private lateinit var q1: LinkedList<Long>
    private lateinit var q2: LinkedList<Long>
    private var limitMove = 0

    fun solution(queue1: IntArray, queue2: IntArray): Int {
        q1 = LinkedList(queue1.map { it.toLong() })
        q2 = LinkedList(queue2.map { it.toLong() })
        limitMove = (q1.size + q2.size) * 2
        return moveCount()
    }

    private fun moveCount(): Int {
        var sum1 = q1.sum()
        var sum2 = q2.sum()
        var mid = (sum1 + sum2) / 2
        var count = 0

        while (count < limitMove) {
            if (sum1 == sum2) break

            if (sum1 < sum2) {
                q2.poll().also {
                    q1.add(it)
                    sum1 += it
                    sum2 -= it
                }
            } else {
                q1.poll().also {
                    q2.add(it)
                    sum1 -= it
                    sum2 += it
                }
            }
            count += 1
        }
        return if (count < limitMove) count else -1
    }
}