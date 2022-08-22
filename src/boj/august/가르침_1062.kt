package boj.august

import java.io.BufferedReader
import java.io.InputStreamReader

private var MAX = 0
private lateinit var words: Array<String>
private val alphabets = ArrayList<Char>()
fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val input = readLine().split(' ').map { it.toInt() }
    val remain = input[1] - 5
    // 단어들 init
    words = Array(input[0]) { readLine() }
    // 알파벳 init
    for (word in words) {
        for (i in 4..word.length - 5) {
            if (!alphabets.contains(word[i]) && word[i] != 'a' && word[i] != 'n'
                && word[i] != 't' && word[i] != 'c' && word[i] != 'i') {
                alphabets.add(word[i])
            }
        }
    }
    val visited = BooleanArray(alphabets.size)
    if (remain >= 0) {
        learnWord(visited, 0, 0, remain)
    }
    println(MAX)
}

private fun learnWord(visited: BooleanArray, start: Int, depth: Int, max: Int) {
    if (depth == max || !visited.contains(false)) {
        MAX = MAX.coerceAtLeast(check(visited))
        return
    }
    for (i in start until alphabets.size) {
        visited[i] = true
        learnWord(visited, i + 1, depth + 1, max)
        visited[i] = false
    }
}

private fun check(visited: BooleanArray): Int {
    var count = 0
    for (word in words) {
        var valid = true
        // 단어를 알 수 있는지 확인
        for (i in 4..word.length - 5) {
            if (word[i] == 'a' || word[i] == 'n'
                || word[i] == 't' || word[i] == 'c' || word[i] == 'i') {
                continue
            }
            val index = alphabets.indexOf(word[i])
            if (!visited[index]) {
                valid = false
                break
            }
        }
        if (valid) {
            count++
        }
    }
    return count
}
