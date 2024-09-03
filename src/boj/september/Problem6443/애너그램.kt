package src.boj.september.Problem6443

private lateinit var alphabet: HashMap<Char, Int>
private lateinit var answer: ArrayList<String>
fun main() = with(System.`in`.bufferedReader()) {
    answer = ArrayList()
    val n = readLine().toInt()
    repeat(n) {
        val input = readLine()
        alphabet = HashMap()
        input.forEach {
            alphabet[it] = alphabet.getOrDefault(it, 0) + 1
        }
        val list = input.toCharArray().distinct().sorted()
        makeWord(list, "", input.length)
    }
    answer.forEach {
        println(it)
    }
}

private fun makeWord(list: List<Char>, result: String, length: Int) {
    if (result.length == length) {
        answer.add(result)
        return
    }
    for (c in list) {
        if (alphabet[c] == 0) continue
        alphabet[c] = alphabet[c]!! - 1
        makeWord(list, result + c, length)
        alphabet[c] = alphabet[c]!! + 1
    }
}
