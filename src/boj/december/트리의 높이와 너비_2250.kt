package boj.december

import java.util.*

private lateinit var tree: Array<LinkedList<Int>>
private lateinit var nodeIndex: IntArray
private lateinit var nodelevel: IntArray
private var length = 1
private var level = 1
private val inOrder = ArrayList<Int>()
fun main() = with(Scanner(System.`in`)) {
    val size = nextInt()
    tree = Array(size + 1) { LinkedList() }
    val rootCheck = BooleanArray(size + 1)
    rootCheck[0] = true
    repeat(size) {
        val root = nextInt()
        val left = nextInt()
        val right = nextInt()
        tree[root].add(left)
        tree[root].add(right)
        if (left != -1) rootCheck[left] = true
        if (right != -1) rootCheck[right] = true
    }
    val root = getRootNode(rootCheck)
    getInOrderList(root)
    nodeIndex = IntArray(size + 1)
    for (i in inOrder.indices) {
        nodeIndex[inOrder[i]] = i
    }
    nodelevel = IntArray(size + 1)
    initNodeLevel(root)
    getMaxLength()
    println("$level $length")
}

private fun getMaxLength() {
    var depth = 1
    while (nodelevel.count { it == depth } != 0) {
        var left = Int.MAX_VALUE
        var right = Int.MIN_VALUE
        for (i in nodelevel.indices) {
            if (nodelevel.count { it == depth } == 0) return
            if (nodelevel[i] == depth) {
                left = left.coerceAtMost(i)
                right = right.coerceAtLeast(i)
            }
        }
        val diff = right - left + 1
        if (length < diff) {
            length = diff
            level = depth
        }
        depth++
    }
}

private fun initNodeLevel(root: Int) {
    val queue = LinkedList<Pair<Int, Int>>()
    queue.offer(Pair(root, 0))
    while (queue.isNotEmpty()) {
        val curr = queue.poll()
        nodelevel[nodeIndex[curr.first]] = curr.second + 1
        for (child in tree[curr.first]) {
            if (child != -1) {
                queue.offer(Pair(child, curr.second + 1))
            }
        }
    }
}

private fun getRootNode(rootCheck: BooleanArray): Int {
    var root = 0
    for (i in rootCheck.indices) {
        if (!rootCheck[i]) {
            root = i
            break
        }
    }
    return root
}

private fun getInOrderList(root: Int) {
    val child = tree[root]
    if (child[0] != -1) getInOrderList(child[0])
    inOrder.add(root)
    if (child[1] != -1) getInOrderList(child[1])
}
