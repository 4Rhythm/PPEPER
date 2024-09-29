package src.boj.september.Problem4358

import java.util.TreeMap

class TrieNode<Char>(var parent: TrieNode<Char>? = null) {
    val children: TreeMap<Char, TrieNode<Char>> = TreeMap()
    var count = 0
    var isTerminating = false
}

class Trie<Char> {
    val root = TrieNode<Char>(parent = null)
    val answer = StringBuilder()
    private var total = 0

    fun insert(list: List<Char>) {
        var current = root
        list.forEach { element ->
            if (current.children[element] == null) {
                current.children[element] = TrieNode(current)
            }
            current = current.children[element]!!
        }
        current.isTerminating = true
        current.count += 1
        total += 1
    }

    fun printNode(current: TrieNode<Char>, word: String = "") {
        if (current.isTerminating) {
            val percent = String.format("%.4f", current.count * 100.0 / total)
            answer.append("$word $percent\n")
        }
        for (node in current.children) {
            printNode(node.value, word + node.key)
        }
    }

}

fun main() = with(System.`in`.bufferedReader()) {
    val trie = Trie<Char>()
    while (true) {
        val name = readLine()
        if (name == null || name.isBlank()) break
        trie.insert(name.toList())
    }
    trie.printNode(trie.root)
    println(trie.answer)
}