package boj.december

import java.io.BufferedReader
import java.io.InputStreamReader

private lateinit var inOrder: List<Int>
private lateinit var postOrder: List<Int>
private lateinit var preOrder: StringBuilder
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val size = readLine().toInt()
    preOrder = StringBuilder()
    inOrder = readLine().split(" ").map { it.toInt() }
    postOrder = readLine().split(" ").map { it.toInt() }
    getPreOrder(0, size - 1, 0, size - 1)
    println(preOrder)
}

private fun getPreOrder(start: Int, end: Int, postOrderStart: Int, postOrderEnd: Int) {
    if (end < start || postOrderEnd < postOrderStart) return
    val root = postOrder[postOrderEnd]
    val rootIndex = inOrder.indexOf(root)
    val leftNode = rootIndex - start
    preOrder.append("$root ")
    getPreOrder(start, rootIndex - 1, postOrderStart, postOrderStart + leftNode - 1)
    getPreOrder(rootIndex + 1, end, postOrderStart + leftNode, postOrderEnd - 1)
}

