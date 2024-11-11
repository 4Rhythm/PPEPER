package src.programmers

import java.util.*

class `주사위 고르기` {

    private lateinit var pick: BooleanArray
    private lateinit var answer: IntArray
    private lateinit var diceSumA: ArrayList<Int>
    private lateinit var diceSumB: ArrayList<Int>
    private lateinit var dices: Array<IntArray>
    private var N = 0
    private var winningRate = 0

    fun solution(dice: Array<IntArray>): IntArray {
        initialize(dice)
        pickDice(0, 0)
        return answer
    }

    private fun calculateWinningRate() {
        // A, B를 나누어 줌
        val a = pick.indices.filter { pick[it] }
        val b = pick.indices.filterNot { pick[it] }
        diceSumA.clear()
        diceSumB.clear()
        rollDice(0, 0, a, diceSumA)
        rollDice(0, 0, b, diceSumB)
        // 승률 구하기
        updateWinningRate(a)
    }

    private fun updateWinningRate(a: List<Int>) {
        diceSumA.sort()
        diceSumB.sort()
        var count = 0
        for (sumA in diceSumA) {
            var left = 0
            var right = diceSumB.size - 1

            while (left <= right) {
                val mid = (left + right) / 2
                if (diceSumB[mid] < sumA) {
                    left = mid + 1
                } else {
                    right = mid - 1
                }
            }
            // A가 이기는 갯수 추가
            count += right + 1
        }
        if (winningRate < count) {
            winningRate = count
            answer = a.map { it + 1 }.toIntArray()
        }
    }

    private fun rollDice(idx: Int, sum: Int, list: List<Int>, diceSum: ArrayList<Int>) {
        if (idx == N) {
            diceSum.add(sum)
            return
        }
        for (i in 0 until 6) {
            rollDice(idx + 1, sum + dices[list[idx]][i], list, diceSum)
        }
    }

    private fun pickDice(start: Int, idx: Int) {
        if (idx == N) {
            calculateWinningRate()
            return
        }
        for (i in start until N * 2) {
            pick[i] = true
            pickDice(i + 1, idx + 1)
            pick[i] = false
        }
    }

    private fun initialize(dice: Array<IntArray>) {
        // 각자 뽑아야하는 Dice의 개수
        N = dice.size / 2
        answer = IntArray(N)
        pick = BooleanArray(dice.size)
        dices = dice
        diceSumA = ArrayList()
        diceSumB = ArrayList()
    }
}