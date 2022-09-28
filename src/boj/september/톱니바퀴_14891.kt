package boj.september

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.pow

private lateinit var list: Array<ArrayList<Int>>
private val direction = intArrayOf(0, 0, 0, 0)
fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    list = Array(4) { ArrayList() }
    // 톱니바퀴 초기화
    for (i in 0 until 4) {
        val value = readLine()
        for (c in value.toList()) {
            list[i].add(c - '0')
        }
    }
    val rotate = readLine().toInt()
    for (j in 0 until rotate) {
        val input = readLine().split(' ').map { it.toInt() }
        val start = input[0] - 1
        val rotateGear = checkRotate(start, input[1])
        for (k in rotateGear.indices) {
            if (rotateGear[k] != 0) {
                gearRotate(list[k], rotateGear[k])
            }
        }
    }
    // score 출력
    println(getScore())

}

private fun checkRotate(start: Int, direction: Int): IntArray {
    var toLeft = direction
    var toRight = direction
    val gearList = intArrayOf(0, 0, 0, 0)
    gearList[start] = direction
    // 뒤에 모든 톱니바퀴 확인
    for (a in start downTo 1) {
        if (list[a - 1][2] != list[a][6]) {
            toLeft = -toLeft
            gearList[a - 1] = toLeft
        } else {
            break
        }
    }
    for (a in start until list.size - 1) {
        if (list[a][2] != list[a + 1][6]) {
            toRight = -toRight
            gearList[a + 1] = toRight
        } else {
            break
        }
    }
    return gearList
}

private fun getScore(): Int {
    var score = 0
    for (i in list.indices) {
        if (list[i][0] == 1) {
            score += 2.0.pow(i.toDouble()).toInt()
        }
    }
    return score
}

private fun gearRotate(list: ArrayList<Int>, i: Int) {
    if (i == 1) {
        val temp = list[list.lastIndex]
        list.apply {
            this.removeLast()
            this.add(0, temp)
        }
    } else {
        val temp = list[0]
        list.apply {
            this.removeFirst()
            this.add(temp)
        }
    }
}