package boj.march

private lateinit var list: List<Int>
private var answer = 0L
fun main() = with(System.`in`.bufferedReader()) {
    val (N, C, W) = readLine().split(" ").map { it.toInt() }
    list = List(N) { readLine().toInt() }
    for (piece in 1..list.max()) {
        var price = 0L
        for (tree in list) {
            val divide = tree / piece
            var slice = divide
            if (divide == 0) continue
            // 딱 나누어 떨어질때
            if (tree % piece == 0) {
                slice -= 1
            }
            val cal = piece * divide * W - slice * C
            if (0 < cal) price += cal
        }
        answer = answer.coerceAtLeast(price)
    }
    println(answer)
}
