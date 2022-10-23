package boj.october

fun main() {
    // 동전의 개수
    var count = 0
    val (N, k) = readln().split(' ').map { it.toInt() }
    var K = k
    // 코인의 종류
    val coinList = mutableListOf<Int>()
    repeat(N) {
        val coin = readln().toInt()
        coinList.add(coin)
    }
    // 동전의 크기가 큰 순서로 나열
    coinList.sortDescending()
    // K원을 만들기 위한 최소의 동전 개수 구하기
    for (coin in coinList) {
        // 동전이 더 작다면 해당 동전으로 최대한 만든다
        if (coin <= K) {
            // 동전의 개수
            count += K / coin
            // 남은 K원
            K %= coin
            // K원을 완성하였다면 종료
            if (K == 0) break
        }
    }
    println(count)
}