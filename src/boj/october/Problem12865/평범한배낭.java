package src.boj.october.Problem12865;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class 평범한배낭 {

    private static int[][] dp;
    private static int[][] input;
    private static int K, N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        K = parseInt(st.nextToken());
        dp = new int[N][K + 1];
        input = new int[N + 1][2];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            input[i][0] = parseInt(st.nextToken());
            input[i][1] = parseInt(st.nextToken());
        }
        System.out.println(search(0, 0));
    }

    private static int search(int idx, int weight) {
        if (K < weight) return Integer.MIN_VALUE;
        if (idx == N) return 0;
        if (dp[idx][weight] != -1) return dp[idx][weight];

        dp[idx][weight] = Math.max(search(idx + 1, weight + input[idx][0]) + input[idx][1], search(idx + 1, weight));
        return dp[idx][weight];
    }

    private static int parseInt(String input) {
        return Integer.parseInt(input);
    }
}
