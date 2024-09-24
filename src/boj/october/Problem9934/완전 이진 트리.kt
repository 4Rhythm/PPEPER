package src.boj.october.Problem9934

private lateinit var treeLevel: Array<ArrayList<Int>>
fun main() = with(System.`in`.bufferedReader()) {
    treeLevel = Array(readLine().toInt()) { ArrayList() }
    val preOrder = readLine().split(" ").map { it.toInt() }
    checkNodeLevel(preOrder, 0, preOrder.size - 1, 0)
    treeLevel.forEach { nodes ->
        println(nodes.joinToString(" "))
    }
}

private fun checkNodeLevel(preOrder: List<Int>, start: Int, end: Int, level: Int) {
    if (start == end) {
        treeLevel[level].add(preOrder[start])
        return
    }
    val parent = (start + end) / 2
    treeLevel[level].add(preOrder[parent])
    // 왼쪽 노드
    checkNodeLevel(preOrder, start, parent - 1, level + 1)
    // 오른쪽 노드
    checkNodeLevel(preOrder, parent + 1, end, level + 1)
}