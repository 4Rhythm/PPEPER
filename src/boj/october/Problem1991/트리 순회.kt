package src.boj.october.Problem1991

class Tree {

    data class Node(
        val data: String,
        var left: Node? = null,
        var right: Node? = null
    )

    var root: Node? = null

    fun add(data: String, left: String, right: String) {
        if (root == null) {
            if (data != ".") root = Node(data)
            if (left != ".") root?.left = Node(left)
            if (right != ".") root?.right = Node(right)
        } else {
            setNode(root!!, data, left, right)
        }
    }

    fun inOrder(root: Node) {
        root.left?.let { inOrder(it) }
        print(root.data)
        root.right?.let { inOrder(it) }
    }

    fun preOrder(root: Node) {
        print(root.data)
        root.left?.let { preOrder(it) }
        root.right?.let { preOrder(it) }
    }

    fun postOrder(root: Node) {
        root.left?.let { postOrder(it) }
        root.right?.let { postOrder(it) }
        print(root.data)
    }

    private fun setNode(root: Node, data: String, left: String, right: String) {
        if (root.data == data) {
            if (left != ".") root.left = Node(left)
            if (right != ".") root.right = Node(right)
            return
        }
        root.left?.let { setNode(it, data, left, right) }
        root.right?.let { setNode(it, data, left, right) }
    }
}
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val tree = Tree()
    repeat(n) {
        val (data, left, right) = readLine().split(" ")
        tree.add(data, left, right)
    }
    tree.run {
        preOrder(tree.root!!)
        println()
        inOrder(tree.root!!)
        println()
        postOrder(tree.root!!)
    }
}