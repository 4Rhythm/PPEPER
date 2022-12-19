package boj.december

import java.lang.StringBuilder
import java.util.Scanner

private lateinit var parent: IntArray
fun main() = with(Scanner(System.`in`)){
    val testCase = nextInt()
    val answer = StringBuilder()
    repeat(testCase) {
        val N = nextInt()
        parent = IntArray(N + 1) { 0 }
        val parentNode1 = ArrayList<Int>()
        val parentNode2 = ArrayList<Int>()
        repeat(N - 1) {
            val a = nextInt()
            val b = nextInt()
            parent[b] = a
        }
        val node1 = nextInt()
        val node2 = nextInt()
        getParentList(node1, parentNode1)
        getParentList(node2, parentNode2)
        answer.append("${getCommonAncestor(parentNode1, parentNode2)}\n")
    }
    println(answer)
}

private fun getCommonAncestor(parentNode1: ArrayList<Int>, parentNode2: ArrayList<Int>): Int {
    for (node in parentNode1) {
        if (parentNode2.contains(node)) {
            return node
        }
    }
    return -1
}

private fun getParentList(node: Int, list: ArrayList<Int>) {
    var node = node
    list.add(node)
    while (node != 0) {
        val p = parent[node]
        list.add(p)
        node = p
    }
}