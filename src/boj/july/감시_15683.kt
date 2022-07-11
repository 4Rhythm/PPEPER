package boj.july

import java.io.BufferedReader
import java.io.InputStreamReader

data class CCTV(
    var number: Int,
    var x: Int,
    var y: Int,
    var rotateCount: Int
)

private val dx = intArrayOf(0, 1, 0, -1) // 상우하좌
private val dy = intArrayOf(-1, 0, 1, 0)
val basic = arrayOf(
    intArrayOf(0),
    intArrayOf(0, 2),
    intArrayOf(0, 1),
    intArrayOf(0, 1, 3),
    intArrayOf(0, 1, 2, 3)
)
var blindSpot = 64
fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val input = readLine().split(' ').map { it.toInt() }
    val list = ArrayList<CCTV>()
    val map = Array(input[0]) { IntArray(input[1]) }
//     map init
    for (i in 0 until input[0]) {
        val number = readLine().split(' ').map { it.toInt() }
        for (j in number.indices) {
            map[i][j] = number[j]
            // cctv가 있으면 저장
            when (number[j]) {
                1 -> {
                    list.add(CCTV(number[j], i, j, 3))
                }
                2 -> {
                    list.add(CCTV(number[j], i, j, 1))
                }
                3 -> {
                    list.add(CCTV(number[j], i, j, 3))
                }
                4 -> {
                    list.add(CCTV(number[j], i, j, 3))
                }
                5 -> {
                    list.add(CCTV(number[j], i, j, 0))
                }
            }
        }
    }
    // 최대 값 구하기
    checkDetectZone(map, 0, list)
    println(blindSpot)
}

private fun checkDetectZone(map: Array<IntArray>, depth: Int, list: ArrayList<CCTV>) {
    if (depth == list.size) {
        // 사각지대 개수
        val count = map.sumOf { array -> array.count { it == 0 } }
        // 사각지대가 적으면 바꿔준다
        blindSpot = blindSpot.coerceAtMost(count)
        return
    } else {
        // 회전에 따라 감시된 map을 다음 cctv에 넘겨줌
        val cctv = list[depth]
        for (rotate in 0..cctv.rotateCount) {
            val detectMap = detect(map, cctv, rotate)
            checkDetectZone(detectMap, depth + 1, list)
        }
    }
}

private fun detect(map: Array<IntArray>, cctv: CCTV, rotate: Int): Array<IntArray> {
    // deep copy
    val detectMap = map.map {
        it.copyOf()
    }.toTypedArray()
    val move = basic[cctv.number - 1]
    // 감시영역
    for (i in move) {
        var x = cctv.x
        var y = cctv.y
        while (true) {
            x += dx[(i + rotate) % 4]
            y += dy[(i + rotate) % 4]
            // 맵을 벗어나지 않음
            if (x >= 0 && x < map.size && y >= 0 && y < map[0].size) {
                when (detectMap[x][y]) {
                    // cctv는 통과
                    1,2,3,4,5 -> { continue }
                    // 벽을 건내뛰면 안됨
                    6 -> { break }
                    else -> { detectMap[x][y] = -1 }
                }
            } else {
                break
            }
        }
    }
    return detectMap
}