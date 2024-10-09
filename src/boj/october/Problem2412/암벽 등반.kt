package src.boj.october.Problem2412

import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs

data class Node(
    val idx: Int,
    val move: Int
)

private lateinit var grooved: ArrayList<Pair<Int, Int>>
private lateinit var visited: BooleanArray
private var answer = Int.MAX_VALUE
fun main() = with(System.`in`.bufferedReader()) {
    val (n, t) = readLine().split(" ").map { it.toInt() }
    grooved = ArrayList<Pair<Int, Int>>().apply { add(Pair(0, 0)) }
    visited = BooleanArray(n + 1)
    repeat(n) {
        val (a, b) = readLine().split(" ").map { it.toInt() }
        grooved.add(Pair(a, b))
    }
    grooved.sortWith(compareBy( { it.first }, { it.second }))
    move(t)
    if (answer == Int.MAX_VALUE) println(-1) else println(answer)
}

private fun move(target: Int) {
    val queue: Queue<Node> = LinkedList()
    queue.add(Node(0, 0))
    visited[0] = true
    while (queue.isNotEmpty()) {
        val (idx, count) = queue.poll()
        if (grooved[idx].second == target) {
            answer = count
            break
        }
        for (i in idx - 1 downTo 0) {
            if (!canMoveX(i, idx)) break
            if (!visited[i] && canMoveY(i, idx)) {
                visited[i] = true
                queue.add(Node(i, count + 1))
            }
        }
        for (i in idx + 1 until grooved.size) {
            if (!canMoveX(i, idx)) break
            if (!visited[i] && canMoveY(i, idx)) {
                visited[i] = true
                queue.add(Node(i, count + 1))
            }
        }
    }
}

private fun canMoveX(from: Int, to: Int): Boolean {
    return abs(grooved[from].first - grooved[to].first) <= 2
}

private fun canMoveY(from: Int, to: Int): Boolean {
    return abs(grooved[from].second - grooved[to].second) <= 2
}