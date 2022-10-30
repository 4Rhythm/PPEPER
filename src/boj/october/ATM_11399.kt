package boj.october

fun main() {
    val people = readln().toInt()
    val atm = readLine()!!.split(' ').map { it.toInt() }.sorted()
    var time = 0
    for (i in atm.indices) {
        for (j in 0..i) {
            time += atm[j]
        }
    }
    println(time)
}