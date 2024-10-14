package src.boj.october.Problem1874

import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val sb = StringBuilder()
    val n = readLine().toInt()
    val stack = Stack<Int>()
    // 랜덤 수열
    var number = 1
    var flag = true
    for (i in 0 until n) {
        val target = readLine().toInt()
        // 이미 들어 간 수
        if (target < number) {
            if (stack.peek() == target) {
                stack.pop()
                sb.append("-\n")
            } else {
                flag = false
            }
        } else {
            while (number <= target) {
                sb.append("+\n")
                stack.push(number++)
            }
            sb.append("-\n")
            stack.pop()
        }
    }
    if (flag) {
        println(sb)
    } else {
        println("NO")
    }
}