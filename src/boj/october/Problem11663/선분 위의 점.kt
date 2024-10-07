package src.boj.october.Problem11663

private lateinit var list: List<Int>
private var answer = StringBuilder()
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    list = readLine().split(" ").map { it.toInt() }.sorted()
    repeat(m) {
        val (left, right) = readLine().split(" ").map { it.toInt() }
        val findLeftIdx = getLeft(left)
        val findRightIdx = getRight(right)
        answer.append(findRightIdx - findLeftIdx + 1).append("\n")
    }
    print(answer)
}

private fun getLeft(target: Int): Int {
    var left = 0
    var right = list.size - 1
    while (left <= right) {
        val mid = (left + right) / 2
        if (list[mid] < target) {
            left = mid + 1
        } else {
            right = mid - 1
        }
    }
    return right + 1
}

private fun getRight(target: Int): Int {
    var left = 0
    var right = list.size - 1
    while (left <= right) {
        val mid = (left + right) / 2
        if (target < list[mid]) {
            right = mid - 1
        } else {
            left = mid + 1
        }
    }
    return right
}
