package programmers

import java.util.*

data class Truck(
    var weight: Int,
    var position: Int = 0
)
class MoveTruck {
    fun solution(bridge_length: Int, weight: Int, truck_weights: IntArray): Int {
        var time = 0
        val trucksQueue: Queue<Int> = LinkedList(truck_weights.toList())
        val onBridge: Deque<Truck> = LinkedList()
        while (trucksQueue.isNotEmpty()) {
            time++
            moveTruck(onBridge, bridge_length)
            // 다리에 트럭이 더 올라 갈 수 있다.
            if (onBridge.sumOf { it.weight } + trucksQueue.peek() <= weight) {
                onBridge.offer(Truck(trucksQueue.poll()))
            }
        }
        // 다리에 트럭이 다올라감
        // 다리에 트럭이 남아있으면 가장 마지만에 들어온 트럭이 나가는 시간만큼이 더 필요하다
        if (onBridge.isNotEmpty()) {
            time += bridge_length - onBridge.pollLast().position
        }
        return time
    }

    private fun moveTruck(onBridge: Queue<Truck>, bridge_length: Int) {
        for (truck in onBridge) {
            truck.position++
        }
        while (onBridge.isNotEmpty()) {
            val truck = onBridge.peek()
            // 다리를 지남
            if (bridge_length == truck.position) {
                onBridge.poll()
            } else {
                break
            }
        }
    }
}

fun main() {
    val test = MoveTruck()
//    println(test.solution(2, 10, intArrayOf(7,4,5,6)))
    println(test.solution(100, 100, intArrayOf(10,10,10,10,10,10,10,10,10,10)))
}