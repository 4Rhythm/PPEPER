package boj.february

fun main() = with(System.`in`.bufferedReader()) {
    val (n, k, q, m) = readLine().split(" ").map { it.toInt() }
    val sleep = readLine().split(" ").map { it.toInt() }
    val qr = readLine().split(" ").map { it.toInt() }
    val students = IntArray(n + 3) { 1 }
    students.fill(0, 0, 3)
    for (check in qr) {
        var idx = 1
        if (!sleep.contains(check)) {
            // 자고 있지 않으면 출석
            while (idx * check < n + 3) {
                students[idx * check] = 0;
                idx++
            }
        }
    }
    sleep.forEach {
        students[it] = 1
    }
    for (k in 4 until students.size) {
        students[k] += students[k - 1]
    }
    for (i in 0 until m) {
        val range = readLine().split(" ").map { it.toInt() }
        println(students[range[1]] - students[range[0] - 1])
    }
}