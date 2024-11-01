package src.programmers;

import java.util.*;

class 입국심사 {
    public long solution(int n, int[] times) {
        return findMinTimeAllPersonChecked(n, times);
    }

    private long findMinTimeAllPersonChecked(int n, int[] times) {
        Arrays.sort(times);
        long left = 0;
        // 최대로 걸릴 수 있는 시간
        long right = (long) times[times.length - 1] * n;

        while (left <= right) {
            long mid = (left + right) / 2;
            // 해당 시간안에 모두 확인가능 -> 더 작은 시간안에 가능한지 확인
            if (countCheckedPerson(times, mid) < n) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right + 1;
    }

    private long countCheckedPerson(int[] times, long endTime) {
        long count = 0;
        for (int time : times) {
            count += endTime / time;
        }
        return count;
    }
}

