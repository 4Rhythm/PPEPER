package boj.february

fun main() = with(System.`in`.bufferedReader()) {
    val (n,k)=readLine().split(" ").map { it.toInt() }
    val diff=readLine().split(" ").map { it.toInt()}.toIntArray()
    (1 until n).forEach { i ->
        diff[i - 1]=diff[i]-diff[i - 1]
    }
    diff.sort()
    // 하나 뽑을때 마다 조의 개수가 하나씩 둘어둠 -> k개뽑기 -> n - k개
    println(diff.take(n - k).sum())
}
