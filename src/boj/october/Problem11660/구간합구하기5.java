package src.boj.october.Problem11660;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class 구간합구하기5 {

    private static int[][] sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder answer = new StringBuilder();
        int n = parseInt(st.nextToken());
        int m = parseInt(st.nextToken());
        int[][] board = new int[n][n];
        sum = new int[n + 1][n + 1];
        for (int i = 0; i < n; i++) {
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum[i + 1][j + 1] = sum[i][j + 1] + sum[i + 1][j] - sum[i][j] + board[i][j];
            }
        }
        for (int i = 0; i <m ; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = parseInt(st.nextToken());
            int y1 = parseInt(st.nextToken());
            int x2 = parseInt(st.nextToken());
            int y2 = parseInt(st.nextToken());
            answer.append(getSumOfRange(x1, y1, x2, y2)).append("\n");
        }
        System.out.print(answer);
    }

    private static int getSumOfRange(int x1, int y1, int x2, int y2) {
        return sum[x2][y2] - sum[x1 - 1][y2] - sum[x2][y1 - 1] + sum[x1 - 1][y1 - 1];
    }

    private static int parseInt(String input) {
        return Integer.parseInt(input);
    }
}
