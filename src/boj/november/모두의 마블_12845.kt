package boj.november

fun main() {
    val size = readln().toInt()
    val list = readLine()!!.split(' ').map { it.toInt() }.toMutableList()
    val (index, max) = getMax(list)
    var gold = 0
    for (a in 0 until index) {
        gold += max + list[a]
    }
    for (b in index + 1 until list.size) {
        gold += max + list[b]
    }
    println(gold)
}

private fun getMax(list: MutableList<Int>): Pair<Int, Int> {
    var max = 0
    var index = 0
    for (i in list.indices) {
        if (max < list[i]) {
            max = list[i]
            index = i
        }
    }
    return Pair(index, max)
}
