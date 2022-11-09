package boj.november

import java.util.*

// 처음 가장 늦게 사용되는 가전제품을 뽑는다
// 추가
// 뒤에 사용할 가전제품 중 개수가 적은 친구 -> 가장 처음 늦게 사용되는 가전제품
fun main() {
    val (N, K) = readLine()!!.split(' ').map { it.toInt() }
    val list = readLine()!!.split(' ').map { it.toInt() }
    val queue = LinkedList<Int>().apply {
        list.forEach {
            offer(it)
        }
    }
    val multiTap = ArrayList<Int>()
    var unplug = 0
    while (queue.isNotEmpty()) {
        // 기존에 사용중이면 쭉 사용
        if (multiTap.contains(queue.peek())) {
            queue.poll()
            continue
        }
        if (multiTap.size == N) {
            val product = queue.poll()
            // 뽑아야하는 가전제품 선택
            val unPlugProduct = choseUnPlug(multiTap, queue)
            multiTap.run {
                remove(unPlugProduct)
                add(product)
            }
            unplug++
        } else {
            multiTap.add(queue.poll())
        }
    }
    println(unplug)
}

private fun choseUnPlug(multiTap: ArrayList<Int>, queue: LinkedList<Int>): Int {
    var whenToUse = 0
    for (product in multiTap) {
        // 나중에 사용이 안됨 -> 먼저 뺀다
        if (!queue.contains(product)) {
            return product
        }
        // 가장 처음 늦게 다시 사용하는 전자제품
        for (i in queue.indices) {
            // 사용할 전자제품
            if (product == queue[i]) {
                whenToUse = whenToUse.coerceAtLeast(i)
                break
            }
        }
    }
    return queue[whenToUse]
}