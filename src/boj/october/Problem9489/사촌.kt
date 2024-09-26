package src.boj.october.Problem9489

import java.util.*


fun main() = with(System.`in`.bufferedReader()) {
    val answer = StringBuilder()
    while (true) {
        var st = StringTokenizer(readLine())
        val n = st.nextToken().toInt()
        val k = st.nextToken().toInt()
        val parent = IntArray(n + 1)
        if (n == 0 && k == 0) break
        var currParentIndex = 0
        var targetNodeIndex = -1
        var currNode = -1
        parent[0] = -1
        st = StringTokenizer(readLine());
        for (i in 1..n) {
            val node = st.nextToken().toInt()
            if (node == k) targetNodeIndex = i
            if (currNode == -1) {
                currNode = node
                continue
            }
            if (currNode + 1 != node) currParentIndex++
            parent[i] = currParentIndex
            currNode = node
        }
        val targetParent = parent[targetNodeIndex]
        val cousins = getCousins(parent, targetParent)
        answer.append("${cousins}\n")
    }
    println(answer)
}

private fun getCousins(parent: IntArray, targetParent: Int): Int {
    var cousins = 0
    for (i in 1 until parent.size) {
        if (parent[i] != targetParent && parent[parent[i]] == parent[targetParent]) {
            cousins++
        }
    }
    return cousins
}