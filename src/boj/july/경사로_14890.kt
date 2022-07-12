package boj.july

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.abs

private var count = 0
fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val input = readLine().split(' ').map { it.toInt() }
    val list = Array(input[0]) { IntArray(input[0]) }
    // 경사로 개수
    val L = input[1]
    for (i in 0 until input[0]) {
        val height = readLine().split(' ').map { it.toInt() }
        // 보드에 숫자 init
        for (j in height.indices) {
            list[i][j] = height[j]
        }
    }
    // 도로 mapper
    val road = roadMapper(list)
    // 도로 확인
    for (i in road.indices) {
        isValidRoad(road[i], L, 0)
    }
    println(count)
}

// 가로방향 길 리턴
private fun roadMapper(list: Array<IntArray>): Array<IntArray> {
    // 가로방향 길 mapping
    val mapper = list.map { it }.toMutableList()
    // 세로 방향 길 mapping
    for (i in list.indices) {
        val vertical = IntArray(list.size)
        for (j in list.indices) {
            vertical[j] = list[j][i]
        }
        mapper.add(vertical)
    }
    return mapper.toTypedArray()
}

// 길을 건날수 있나 확인
private fun isValidRoad(road: IntArray, L: Int, start: Int) {
    // 길을 다 건넘 -> valid
    if (start == road.size - 1) {
        count++
        return
    } else {
        val heightDiff = road[start] - road[start + 1]
        // 높이차이가 1보다 크면 이동불가능
        if (1 < abs(heightDiff)) {
            return
        } else if (heightDiff == 0) {
            // 1. 그냥 이동
            isValidRoad(road, L, start + 1)
            // 2. L 길이의 다리를 둘 수 있나 확인
            if (start + L < road.size && isValidBride(road, L, start, heightDiff)) {
                isValidRoad(road, L, start + L)
            }
        } else if (heightDiff == 1) {
            // L길이의 다리를 둘 수 있나 확인
            if (start + L < road.size && isValidBride(road, L, start + 1, heightDiff)) {
                if (start + L + 1 < road.size) {
                    isValidRoad(road, L, start + L + 1)
                } else {
                    isValidRoad(road, L, start + L)
                }
            }
        } else {
            if (start + 1 < road.size && L == 1) {
                isValidRoad(road, L, start + 1)
            }
        }
    }
    return
}

// 다리를 둘 수 있나 확인
private fun isValidBride(road: IntArray, L: Int, start: Int, direction: Int): Boolean {
    val current = road[start]
    var equalHeight = false
    var diff = -100
    // 다리를 두고 난 이후 다음 road가 있을때
    if (start + L < road.size) {
        diff = road[start + L - 1] - road[start + L]
    }
    // 도로의 높이가 같은지 확인
    if (start + L - 1 < road.size) {
        for (i in start until start + L) {
            if (current == road[i]) {
                equalHeight = true
            } else {
                equalHeight = false
                break
            }
        }
    }
    // 다리를 둘 수 있을때 방향에 따라 높이 차이 확인
    return if (equalHeight) {
        if (direction == 0) {
            diff == -1 || diff == -100
        } else { // direction == 1
            if (diff == -100) {
                true
            } else {
                when (diff) {
                    0 -> { true }
                    1 -> {
                        isValidBride(road, L, start + L, direction)
                    }
                    else -> { false }
                }
            }
        }
    } else {
        false
    }
}