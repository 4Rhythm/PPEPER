package src.programmers;

import java.util.*;

class 영어_끝말잇기 {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        HashSet<String> wordSet = new HashSet<>();
        char prev = words[0].charAt(0);
        for (int i = 0; i < words.length; i++) {
            // 1. 중복된 수인지 확인
            if (wordSet.contains(words[i])) {
                answer[0] = i % n + 1;
                answer[1] = i / n + 1;
                break;
            }
            wordSet.add(words[i]);
            char curr = words[i].charAt(0);
            if (prev != curr) {
                answer[0] = i % n + 1;
                answer[1] = i / n + 1;
                break;
            }
            prev = words[i].charAt(words[i].length() - 1);
        }

        return answer;
    }
}