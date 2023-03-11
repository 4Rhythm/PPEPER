package boj.march

fun main() {
    val n = readln().toInt()
    val pattern = readLine()!!.split("*")
    val list = List(n) { readLine()!! }
    // 앞 뒤를 확인
    list.forEach { str ->
        if (str.length < pattern[0].length) println("NE")
        else {
            if (str.length < pattern[0].length + pattern[1].length) {
                println("NE")
            } else {
                val front = str.substring(0, pattern[0].length)
                val rear = str.substring(str.length - pattern[1].length)
                if (front == pattern[0] && rear == pattern[1]) println("DA")
                else println("NE")
            }
        }
    }
}