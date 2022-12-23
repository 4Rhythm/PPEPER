package boj.december

import java.lang.StringBuilder

private lateinit var preOrder: List<Int>
private lateinit var inOrder: List<Int>
private var index = 0
private lateinit var postOrder: StringBuilder
fun main() {
    val testCase = readln().toInt()
    val answer = StringBuilder()
    repeat(testCase) {
        val size = readln().toInt()
        index = 0
        preOrder = readLine()!!.trim().split(' ').map { it.toInt() }
        inOrder = readLine()!!.trim().split(' ').map { it.toInt() }
        postOrder = StringBuilder()
        getPostOrder(0, size - 1)
        answer.append("$postOrder\n")
    }
    println(answer)
}

private fun getPostOrder(start: Int, end: Int) {
    if (end < start) return
    val root = preOrder[index++]
    val leftIndex = inOrder.indexOf(root)
    getPostOrder(start, leftIndex - 1)
    getPostOrder(leftIndex + 1, end)
    postOrder.append("$root ")
}
