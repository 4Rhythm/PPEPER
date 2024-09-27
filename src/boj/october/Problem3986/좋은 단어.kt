package src.boj.october.Problem3986

fun main() = with(System.`in`.bufferedReader()) {
    print(
        List(readLine().toInt()) { readLine() }.count { word ->
            val list = mutableListOf<Char>()
            word.forEach {
                if (it == list.lastOrNull()) list.removeLastOrNull() else list.add(it)
            }
            list.isEmpty()
        }
    )
}