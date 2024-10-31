package src.boj.october.Problem11048;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class 이동하기 {

    private static int n, m;
    private static int[][] board, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = parseInt(st.nextToken());
        m = parseInt(st.nextToken());
        board = new int[n][m];
        dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Arrays.fill(dp[i], -1);
        }
        System.out.println(search(n - 1, m - 1));
    }

    private static int search(int x, int y) {
        if (x < 0 || y < 0) return 0;
        if (dp[x][y] != -1) return dp[x][y];

        int buffer = Math.max(search(x - 1, y), search(x, y - 1));
        int result = Math.max(buffer, search(x - 1, y - 1));
        dp[x][y] = result + board[x][y];
        return dp[x][y];
    }

    private static int parseInt(String input) {
        return Integer.parseInt(input);
    }
}
