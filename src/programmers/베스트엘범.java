package src.programmers;

import java.util.*;

class Music implements Comparable<Music> {
    int id, plays;

    public Music(int id, int plays) {
        this.id = id;
        this.plays = plays;
    }

    @Override
    public int compareTo(Music o) {
        if (this.plays == o.plays) {
            return this.id - o.id;
        }
        return o.plays - this.plays;
    }
}

class 베스트엘범 {

    private HashMap<String, Integer> genresPlaysCountMap;
    private HashMap<String, PriorityQueue<Music>> genresIdMap;
    private ArrayList<Integer> result;

    public int[] solution(String[] genres, int[] plays) {
        initinalize(genres, plays);
        filterEachTwoGenresSortedByPlaysDescending();
        return convertToIntArray();
    }

    private void initinalize(String[] genres, int[] plays) {
        genresPlaysCountMap = new HashMap<>();
        genresIdMap = new HashMap<>();
        result = new ArrayList<>();
        for (int i = 0; i < genres.length; i++) {
            // 각 곡마다의 플레이 횟수 저장
            genresPlaysCountMap.put(genres[i], genresPlaysCountMap.getOrDefault(genres[i], 0) + plays[i]);
            // 각 곡에 대한 유니크 ID 값들 저장
            if (genresIdMap.containsKey(genres[i])) {
                genresIdMap.get(genres[i]).add(new Music(i, plays[i]));
                continue;
            }
            PriorityQueue<Music> pq = new PriorityQueue<>();
            pq.add(new Music(i, plays[i]));
            genresIdMap.put(genres[i], pq);
        }
    }

    private void filterEachTwoGenresSortedByPlaysDescending() {
        List<String> genresSortedList = new ArrayList<>(genresPlaysCountMap.keySet());
        genresSortedList.sort((o1, o2) -> genresPlaysCountMap.get(o2) - genresPlaysCountMap.get(o1));

        for (String genres : genresSortedList) {
            // 최대 두곡식 수록
            PriorityQueue<Music> pq = genresIdMap.get(genres);
            int count = 0;
            while (!pq.isEmpty() && count < 2) {
                result.add(pq.poll().id);
                count++;
            }
        }
    }

    private int[] convertToIntArray() {
        int[] answer = new int[result.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }
}