package boj.august

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val size = readLine().toInt()
    val cycleList = IntArray(size)
    for (i in 0 until size) {
        val inputSize = readLine().toInt()
        val parent = Array(inputSize) { n -> n }
        val node = readLine().split(' ').map { it.toInt() - 1 }
        val cycle = checkCycle(parent, node)
        cycleList[i] = cycle
    }
    cycleList.forEach {
        println("$it")
    }
}

private fun checkCycle(parent: Array<Int>, node: List<Int>): Int {
    var cycle = 0
    val visited = BooleanArray(parent.size)
    for (i in node.indices) {
        if (!visited[i]) {
            visited[i] = true
            // 사이클 형성여부
            if (getParentNode(parent, i) != getParentNode(parent, node[i])) {
                // 연결관계 설정
                parent[node[i]] = getParentNode(parent, i)
            } else {
                cycle++
            }
        }
    }
    return cycle
}

private fun getParentNode(parent: Array<Int>, node: Int): Int {
    if (parent[node] != node) {
        parent[node] = getParentNode(parent, parent[node])
    }
    return parent[node]
}
