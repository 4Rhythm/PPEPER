package src.boj.october.programmers

import java.util.*
import kotlin.math.max

data class Course(
    val to: Int,
    val cost: Int
): Comparable<Course> {
    override fun compareTo(other: Course): Int {
        return this.cost - other.cost
    }
}

class `등산코스 정하기` {

    private val INF = 987654321
    private var N = 0
    private lateinit var summitSet: HashSet<Int>
    private lateinit var graph: Array<LinkedList<Course>>
    private lateinit var visited: BooleanArray
    // 쉬지 않고 등산할때에서의 가장 큰 cost
    private lateinit var intensity: IntArray

    fun solution(n: Int, paths: Array<IntArray>, gates: IntArray, summits: IntArray): IntArray {
        init(n, paths, summits)
        return findRoute(gates, summits.sortedArray())
    }

    private fun findRoute(gates: IntArray, summits: IntArray): IntArray {
        // 현재 산봉우리
        var currentSummit = INF
        var currentIntensity = INF
        val pq = PriorityQueue<Course>()
        for (gate in gates) {
            pq.add(Course(gate, 0))
            intensity[gate] = 0
        }

        // 최소 intensity 구하기
        while (pq.isNotEmpty()) {
            val (node, cost) = pq.poll()

            if (intensity[node] < cost) continue
            if (summitSet.contains(node)) continue

            for (nextCourse in graph[node]) {
                // 산봉우리까지 가는데 쉬는 시간이 더 작은 값
                val maxCost = max(cost, nextCourse.cost)
                if (maxCost < intensity[nextCourse.to]) {
                    intensity[nextCourse.to] = maxCost
                    pq.add(Course(nextCourse.to, maxCost))
                }
            }
        }
        println(intensity.toList())
        // 최소 summit, 최소 intensity 구하기
        for (summit in summits) {
            if (intensity[summit] < currentIntensity) {
                currentIntensity = intensity[summit]
                currentSummit = summit
            }
        }
        return intArrayOf(currentSummit, currentIntensity)
    }

    private fun init(n: Int, paths: Array<IntArray>, summits: IntArray) {
        N = n
        summitSet = summits.toHashSet()
        visited = BooleanArray(n + 1)
        intensity = IntArray(n + 1) { INF }
        graph = Array(n + 1) { LinkedList() }
        paths.forEach { (from, to, cost) ->
            graph[from].add(Course(to, cost))
            graph[to].add(Course(from, cost))
        }
    }
}

fun main() {
    val test = `등산코스 정하기`()
    val n = 6
    val paths = arrayOf(
        intArrayOf(1, 2, 3),
        intArrayOf(2, 3 ,5),
        intArrayOf(2, 4, 2),
        intArrayOf(2, 5, 4),
        intArrayOf(3, 4, 4),
        intArrayOf(4, 5, 3),
        intArrayOf(4, 6, 1),
        intArrayOf(5, 6, 1)
    )
    val gates = intArrayOf(1, 3)
    val summits = intArrayOf(5)

    val n2 = 7
    val paths2 = arrayOf(
        intArrayOf(1, 4, 4),
        intArrayOf(1, 6, 1),
        intArrayOf(1, 7, 3),
        intArrayOf(2, 5, 2),
        intArrayOf(3, 7, 4),
        intArrayOf(5, 6, 6),
    )
    val gates2 = intArrayOf(1)
    val summits2 = intArrayOf(2, 3, 4)
    println(test.solution(n, paths, gates, summits).toList())
    println(test.solution(n2, paths2, gates2, summits2).toList())
}