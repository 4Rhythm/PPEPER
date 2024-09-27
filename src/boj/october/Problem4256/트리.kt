package src.boj.october.Problem4256

/**
 * PreOrder : 가운데, 왼쪽, 오른쪽
 * InOrder : 왼쪽, 가운데, 오른쪽
 * PostOrder : 왼쪽, 오른쪽, 가운데
 */
private lateinit var preOrder: List<Int>
private lateinit var inOrder: List<Int>
private lateinit var postOrder: StringBuilder
private var answer = StringBuilder()
private var index = 0
fun main() = with(System.`in`.bufferedReader()) {
    val t = readLine().toInt()
    repeat(t) {
        val n = readLine().toInt()
        postOrder = StringBuilder()
        preOrder = readLine().trim().split(" ").map { it.toInt() }
        inOrder = readLine().trim().split(" ").map { it.toInt() }
        index = 0
        getPostOrder(0, n - 1)
        answer.append(postOrder).append("\n")
    }
    print(answer)
}

private fun getPostOrder(start: Int, end: Int) {
    if (end < start) return
    val root = preOrder[index++]
    val leftIndex = inOrder.indexOf(root)
    getPostOrder(start, leftIndex - 1)
    getPostOrder(leftIndex + 1, end)
    postOrder.append("$root ")
}
