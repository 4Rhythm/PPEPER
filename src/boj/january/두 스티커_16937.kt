package boj.january

import java.lang.Integer.max

private var H = 0
private var W = 0
fun main() = with(System.`in`.bufferedReader()) {
    val (h, w) = readLine().split(" ").map { it.toInt() }
    H = h
    W = w
    var stickerMax = 0
    val size = readLine().toInt()
    val stickers = List(size) {
        readLine().split(" ").map { it.toInt() }
    }
    for (i in 0 until stickers.size - 1) {
        val s1 = stickers[i]
        if (!checkSize(s1)) continue
        for (j in i + 1 until stickers.size) {
           // 다음 스티커가 모눈 종이에 들어오는지 확인
            val s2 = stickers[j]
            // 가로 세로 양쪽으로 바꾼것을 다확인 해봐야 한다.
            if (s1[0] + s2[0] <= H && max(s1[1], s2[1]) <= W || max(s1[0], s2[0]) <= H && s1[1] + s2[1] <= W) {
                stickerMax = stickerMax.coerceAtLeast(s1[0] * s1[1] + s2[0] * s2[1])
            }
            if (s1[1] + s2[1] <= H && max(s1[0], s2[0]) <= W || max(s1[1], s2[1]) <= H && s1[0] + s2[0] <= W) {
                stickerMax = stickerMax.coerceAtLeast(s1[0] * s1[1] + s2[0] * s2[1])
            }
            if (s1[0] + s2[1] <= H && max(s1[1], s2[0]) <= W || max(s1[0], s2[1]) <= H && s1[1] + s2[0] <= W) {
                stickerMax = stickerMax.coerceAtLeast(s1[0] * s1[1] + s2[0] * s2[1])
            }
            if (s1[1] + s2[0] <= H && max(s1[0], s2[1]) <= W || max(s1[1], s2[0]) <= H && s1[0] + s2[1] <= W) {
                stickerMax = stickerMax.coerceAtLeast(s1[0] * s1[1] + s2[0] * s2[1])
            }
        }
    }
    println(stickerMax)
}

private fun checkSize(sticker: List<Int>): Boolean {
    return sticker[0] <= H || sticker[0] <= W || sticker[1] <= H || sticker[1] <= W
}