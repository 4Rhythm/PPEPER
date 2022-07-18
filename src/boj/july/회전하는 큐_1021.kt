package boj.july

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val input = readLine().split(' ').map { it.toInt() }
    // 뽑으려는 타겟의 위치
    val targets = readLine().split(' ').map { it.toInt() }
    val deque: Deque<Int> = ArrayDeque()
    // deque init
    repeat(input[0]) {
        deque.offer(it + 1)
    }
    // 2,3번의 연산 개수합
    var count = 0
    // 모두 뽑기
    for (target in targets) {
        while (true) {
            // 뽑으려는 타겟
            if (target == deque.peek()) {
                deque.poll()
                break
            }
            // 2번 연산이 최소 -> <=인 이유는 홀수 index일때 2번으로가 더빠름
            // ex) 1,2,3,4,5 / tartget = 3, deque.size / 2 = 2
            // 2번연산: 2번 / 3번연산: 3번
            if (deque.indexOf(target) <= deque.size / 2) {
                // 뽑으려는 타겟을 맨 앞으로까지의 연산
                while (deque.peek() != target) {
                    deque.offerLast(deque.pollFirst())
                    count++
                }
            } else { // 3번 연산이 최소
                while (deque.peek() != target) {
                    deque.offerFirst(deque.pollLast())
                    count++
                }
            }
        }
    }
    println(count)
}