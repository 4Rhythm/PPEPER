package boj.october

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

// 나이가 어린순으로 정렬
data class Tree(
    val x: Int,
    val y: Int,
    var age: Int
    ): Comparable<Tree> {
    override fun compareTo(other: Tree) = this.age - other.age
}
// 모든 방향
private val dx = intArrayOf(1, 1, 1, 0, 0, -1, -1, -1)
private val dy = intArrayOf(0, 1, -1, 1, -1, 0, 1, -1)
private lateinit var food: Array<IntArray>
private lateinit var amount: Array<IntArray>
private lateinit var tree: PriorityQueue<Tree>
fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (N, M, K) = readLine().split(' ').map { it.toInt() }
    amount = Array(N) { IntArray(N) }
    food = Array(N) { IntArray(N) { 5 } }
    tree = PriorityQueue()
    // 겨울에 추가될 양분
    for (i in 0 until N) {
        amount[i] = readLine().split(' ').map { it.toInt() }.toIntArray()
    }
    // 처음 나무들
    for (j in 0 until M) {
        val (x, y, age) = readLine().split(' ').map { it.toInt() }
        tree.offer(Tree(x-1, y-1, age))
    }
    // 계절이 쭉 지난다
    yearsLater(K)
    println(tree.size)
}

private fun yearsLater(k: Int) {
    var year = k
    while (0 < year) {
        // 봄 -> 나무가 자신의 나이만큼 양분을 먹고, 나이가 1 증가
        // 나이가 어린 나무부터 양분을 먹는다
        // 땅에 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없는 나무는 양분을 먹지 못하고 즉시 죽는다.
        val dead = treeGetOlder()
        // 여름
        treeAddFood(dead)
        // 가을
        // 번식하는 나무는 나이가 5의 배수이어야 하며, 인접한 8개의 칸에 나이가 1인 나무가 생긴다
        breeding()
        // 겨울
        // 땅을 돌아다니면서 땅에 양분을 추가
        addFood()
        year--
    }
}

private fun addFood() {
    for (i in food.indices) {
        for (j in food[i].indices) {
            food[i][j] += amount[i][j]
        }
    }
}

private fun breeding() {
    val copy = PriorityQueue(tree)
    while (tree.isNotEmpty()) {
        val curr = tree.poll()
        if (curr.age % 5 == 0) {
            for (i in 0 until 8) {
                val cx = curr.x + dx[i]
                val cy = curr.y + dy[i]
                if (isValid(cx, cy)) {
                    copy.offer(Tree(cx, cy, 1))
                }
            }
        }
    }
    tree = copy
}

private fun treeAddFood(dead: ArrayList<Tree>) {
    // 각각의 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가
    for (tree in dead) {
        food[tree.x][tree.y] += tree.age / 2
    }
}

private fun treeGetOlder(): ArrayList<Tree> {
    val dead = ArrayList<Tree>()
    val older = PriorityQueue<Tree>()
    while (tree.isNotEmpty()) {
        val curr = tree.poll()
        // 양분을 먹을 수 있어야 한다
        if (curr.age <= food[curr.x][curr.y]) {
            food[curr.x][curr.y] -= curr.age
            older.add(Tree(curr.x, curr.y, curr.age + 1))
        } else {
            dead.add(curr)
        }
    }
    tree = older
    return dead
}

private fun isValid(x: Int, y: Int): Boolean {
    return 0 <= x  && x < food.size && 0 <= y && y < food.size
}
