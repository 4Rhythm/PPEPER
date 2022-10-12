package boj.october

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.HashSet
import kotlin.math.abs

data class Marble(
    var redX: Int,
    var redY: Int,
    var blueX: Int,
    var blueY: Int,
    var move: Int
)

// 상하우좌
private val dx = intArrayOf(-1, 1, 0, 0)
private val dy = intArrayOf(0, 0, 1, -1)
private lateinit var board: Array<Array<Char>>
private lateinit var visited: HashSet<Marble>
fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (N, M) = readLine().split(' ').map { it.toInt() }
    board = Array(N) { Array(M) { '0' } }
    visited = HashSet()
    var redX = 0
    var redY = 0
    var blueX = 0
    var blueY = 0
    for (i in 0 until N) {
        val input = readLine()
        for (j in input.indices) {
            if (input[j] == 'B') {
                blueX = i
                blueY = j
            } else if (input[j] == 'R') {
                redX = i
                redY = j
            }
            board[i][j] = input[j]
        }
    }
    println(playGame(Marble(redX, redY, blueX, blueY, 0)))
}

private fun playGame(marble: Marble): Int {
    val queue: Queue<Marble> = LinkedList()
    queue.offer(marble)
    visited.add(marble)
    while (queue.isNotEmpty()) {
        val curr = queue.poll()
        if (10 <= curr.move) {
            continue
        }
        for (i in 0 until 4) {
            val red = move(curr.redX, curr.redY, i)
            val blue = move(curr.blueX, curr.blueY, i)
            val redDiff = abs(curr.redX - red.first) + abs(curr.redY - red.second)
            val blueDiff = abs(curr.blueX - blue.first) + abs(curr.blueY - blue.second)

            if (red.first == -1 && blue.first == -1) {
                continue
            } else if (red.first == -1) {
                return curr.move + 1
            } else if (blue.first == -1) {
                continue
            } else {
                if (red == blue) {
                    if (redDiff < blueDiff) {
                        val marble =
                            Marble(red.first, red.second, blue.first - dx[i], blue.second - dy[i], curr.move + 1)
                        if (!visited.contains(marble)) {
                            queue.offer(marble)
                            visited.add(marble)
                        }
                    } else {
                        val marble =
                            Marble(red.first - dx[i], red.second - dy[i], blue.first, blue.second, curr.move + 1)
                        if (!visited.contains(marble)) {
                            queue.offer(marble)
                            visited.add(marble)
                        }
                    }
                } else {
                    val marble =
                        Marble(red.first, red.second, blue.first, blue.second, curr.move + 1)
                    if (!visited.contains(marble)) {
                        queue.offer(marble)
                        visited.add(marble)
                    }
                }
            }
        }
    }
    return -1
}

private fun move(x: Int, y: Int, dir: Int): Pair<Int, Int> {
    var cx = x
    var cy = y
    while (true) {
        cx += dx[dir]
        cy += dy[dir]
        if (board[cx][cy] == '#') {
            return Pair(cx - dx[dir], cy - dy[dir])
        } else if (board[cx][cy] == 'O') {
            return Pair(-1, -1)
        }
    }
}
