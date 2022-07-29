package boj.july

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

data class Shark(
    var size: Int,
    var eat: Int,
    var x: Int,
    var y: Int
)
data class Fish(
    var distance: Int,
    var x: Int,
    var y: Int
): Comparable<Fish> {
    override fun compareTo(other: Fish): Int {
        if (distance == other.distance) {
            // 같은 위치에 있으면 -> 가장 왼쪽
            if (x == other.x) { return y - other.y }
            // 위에있는 물고기부터 확인
            return x - other.x
        }
        // 거리가 짧은순으로 비교
        return distance - other.distance
    }
}

private val dx = intArrayOf(0, -1, 0, 1)
private val dy = intArrayOf(1, 0, -1, 0)
private lateinit var MAP: Array<IntArray>
private var TIME = 0
fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val size = readLine().toInt()
    // 아기상어
    lateinit var shark: Shark
    MAP = Array(size) { IntArray(size) }
    // 수족관 init
    for (i in 0 until size) {
        val input = readLine().split(' ').map { it.toInt() }.toIntArray()
        // 처음 상어위치
        if (input.contains(9)) {
            shark = Shark(2, 0, i, input.indexOf(9))
        }
        MAP[i] = input
    }
    // 먹을 수 있는 물고기 확인
    while (true) {
        val list = moveToEat(shark)
        if (list.isEmpty()) {
            break
        } else {
            // 가장 최소 이동
            val fish = list.poll()
            // 상어 처음 위치 -> 0
            MAP[shark.x][shark.y] = 0
            // 먹은 물고기 -> 0
            MAP[fish.x][fish.y] = 0
            // 상어 위치 update
            shark.x = fish.x
            shark.y = fish.y
            // 물고기를 크기 만큼 먹었다면
            if (shark.eat + 1 == shark.size) {
                shark.eat = 0
                shark.size++
            } else {
                shark.eat++
            }
            // 시간 증가
            TIME += fish.distance
        }
    }
    println(TIME)
}
private fun moveToEat(shark: Shark): PriorityQueue<Fish> {
    val visited = Array(MAP.size) { BooleanArray(MAP.size) }
    // 먹을 수 있는 물고기
    val list = PriorityQueue<Fish>()
    val queue: Queue<Fish> = LinkedList()
    queue.offer(Fish(0, shark.x, shark.y))
    visited[shark.x][shark.y] = true
    while (queue.isNotEmpty()) {
        val v = queue.poll()
        // 먹을 수 있는 물고기
        if (MAP[v.x][v.y] in 1 until shark.size) {
            list.add(Fish(v.distance, v.x, v.y))
            continue
        }
        // 상어 이동
        for (i in 0 until 4) {
            val dx = v.x + dx[i]
            val dy = v.y + dy[i]
            // 범위 확인
            if (isValid(dx, dy) && MAP[dx][dy] <= shark.size && !visited[dx][dy]) {
                queue.offer(Fish(v.distance + 1, dx, dy))
                visited[dx][dy] = true
            }
        }
    }
    return list
}

private fun isValid(x: Int, y: Int): Boolean {
    return (x >= 0 && x < MAP.size && y >= 0 && y < MAP.size)
}