package boj.august

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.abs

private var MIN = Int.MAX_VALUE
private lateinit var link: Array<IntArray>
fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val size = readLine().toInt()
    link = Array(size) { IntArray(size) }
    // link 값 init
    for (i in 0 until size) {
        val input = readLine().split(' ').map { it.toInt() }
        link[i] = input.toIntArray()
    }
    val visited = BooleanArray(size)
    selectTeam(visited, 0, 0)
    println(MIN)
}

// 팀 나누기
private fun selectTeam(visited: BooleanArray, start: Int, depth: Int) {
    if (depth == visited.size / 2) {
        compareTeamScore(visited)
        return
    }
    for (i in start until visited.size) {
        visited[i] = true
        selectTeam(visited, i + 1, depth + 1)
        visited[i] = false
    }
}

// 점수 비교
fun compareTeamScore(visited: BooleanArray) {
    var team1 = 0
    var team2 = 0
    // 모든 조합으로 점수를 구한다
    for (i in 0 until visited.size - 1) {
        for (j in i + 1 until visited.size) {
            if (visited[i] && visited[j]) {
                team1 += link[i][j] + link[j][i]
            } else if (!visited[i] && !visited[j]) {
                team2 += link[i][j] + link[j][i]
            }
        }
    }
    // 더 작은 차이값으로 바꿈
    MIN = MIN.coerceAtMost(abs(team1 - team2))
}
