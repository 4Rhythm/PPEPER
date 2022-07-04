package boj.july

import java.io.BufferedReader
import java.io.InputStreamReader

data class Area(
    var number: Int,
    val medal: ArrayList<Int>
)

fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val input = readLine().split(' ').map { it.toInt() }
    val check = ArrayList<Int>()
    val list = ArrayList<Area>()
    for (i in 0 until input[0]) {
        val area = readLine().split(' ').map { it.toInt() }
        list.add(Area(area[0], arrayListOf(area[1], area[2], area[3])))
        // 찾는 등수의 매달
        if (area[0] == input[1]) {
            check.add(area[1])
            check.add(area[2])
            check.add(area[3])
        }
    }
    list.sortWith(compareByDescending<Area> {
        it.medal[0]
    }.thenByDescending {
        it.medal[1]
    }.thenByDescending {
        it.medal[2]
    })
    // 등수 찾기
    for (i in list.indices) {
        if (list[i].medal == check) {
            println(i + 1)
            break
        }
    }
}
