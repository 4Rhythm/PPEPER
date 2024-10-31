package src.boj.october.Problem10800;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class 컬러볼 {

    static class Ball {
        int number, color, size;

        public Ball(int number, int color, int size) {
            this.number = number;
            this.color = color;
            this.size = size;
        }
    }

    private static int n;
    private static Ball[] balls;
    private static StringBuilder answer;
    private static int[] sum_of_ball_number;
    private static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = parseInt(br.readLine());
        result = new int[n];
        balls = new Ball[n];
        answer = new StringBuilder();
        sum_of_ball_number = new int[n + 1];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int color = parseInt(st.nextToken());
            int size = parseInt(st.nextToken());
            balls[i] = new Ball(i, color, size);
        }
        Arrays.sort(balls, (o1, o2) -> {
            if (o1.size == o2.size) {
                if (o1.color == o2.color) {
                    return o1.number - o2.number;
                }
                return o1.color - o2.color;
            }
            return o1.size - o2.size;
        });
        int sum = 0;
        int idx = 0;
        for (int i = 0; i < n; i++) {
            Ball current = balls[i];
            while (balls[idx].size < current.size) {
                sum += balls[idx].size;
                sum_of_ball_number[balls[idx].color] += balls[idx].size;
                idx += 1;
            }
            result[current.number] = sum - sum_of_ball_number[current.color];
        }
        for (int i = 0; i < n; i++) {
            answer.append(result[i]).append("\n");
        }
        System.out.print(answer);
    }

    private static int parseInt(String input) {
        return Integer.parseInt(input);
    }
}
