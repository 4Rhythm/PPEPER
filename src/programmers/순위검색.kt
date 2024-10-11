package src.programmers

import java.util.*

class 순위검색 {
    private lateinit var foodMap: HashMap<String, ArrayList<Int>>
    private lateinit var answer: IntArray

    fun solution(info: Array<String>, query: Array<String>): IntArray {
        initAllData(info, query)
        executeQuery(query)
        return answer
    }

    private fun executeQuery(query: Array<String>) {
        for (idx in query.indices) {
            val (key, score) = query[idx].replace(" and ", "").split(" ")
            foodMap[key]?.let { list ->
                answer[idx] = getFoodCount(list, score.toInt())
            }
        }
    }

    private fun getFoodCount(list: ArrayList<Int>, targetScore: Int): Int {
        var left = 0
        var right = list.lastIndex

        while (left <= right) {
            val mid = (left + right) / 2
            if (list[mid] < targetScore) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
        return list.size - left
    }

    private fun initAllData(info: Array<String>, query: Array<String>) {
        answer = IntArray(query.size)
        foodMap = HashMap()
        // info에서 나올 수 있는 모든 조합을 구한다.
        info.forEach {
            search("", 0, it.split(" "))
        }
        for (key in foodMap.keys) {
            foodMap[key]?.sort()
        }
    }

    private fun search(result: String, depth: Int, input: List<String>) {
        if (depth == 4) {
            // 해당 key값 조합에 대한 점수를 저장
            foodMap[result]?.let { list ->
                list.add(input[4].toInt())
            } ?: run {
                foodMap[result] = arrayListOf(input[4].toInt())
            }
            return
        }
        search(result + "-", depth + 1, input)
        search(result + input[depth], depth + 1, input)
    }
}