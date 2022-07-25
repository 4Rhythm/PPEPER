package programmers

class Number {
    fun solution(numbers: IntArray): String {
        // 문자열로 변환
        val list = numbers.map { it.toString() }.toMutableList()
        // 가장 크게 만들수 있게 정렬
        list.sortWith { o1, o2 -> (o2 + o1).compareTo(o1 + o2) }
        val answer = list.joinToString("")
        // 문자열이 0으로 시작할때
        return if (answer.startsWith("0")) {
            "0"
        } else {
            answer
        }
    }
}