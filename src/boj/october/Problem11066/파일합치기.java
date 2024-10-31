package src.boj.october.Problem11066;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class 파일합치기 {

    private static int k;
    private static StringBuilder answer;
    private static int[] sum, list;
    private static int[][] dp;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = parseInt(br.readLine());
        answer = new StringBuilder();
        for (int i = 0; i < t; i++) {
            k = parseInt(br.readLine());
            list = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            sum = new int[k + 1];
            dp = new int[k][k];
            for (int j = 0; j < k; j++) {
                Arrays.fill(dp[j], Integer.MAX_VALUE);
                dp[j][j] = list[j];
                sum[j + 1] = sum[j] + list[j];
            }
            answer.append(search(0, k - 1)).append("\n");
        }
        System.out.println(answer);
    }

    // 합쳐질 수 있는 파일들은 옆에 인접한 파일들만
    private static int search(int left, int right) {
        if (right <= left) return 0;
        if (left + 1 == right) {
            dp[left][right] = dp[left][left] + dp[right][right];
        }
        if (dp[left][right] != Integer.MAX_VALUE) return dp[left][right];

        for (int i = left; i < right; i++) {
            dp[left][right] = Math.min(
                    dp[left][right],
                    search(left, i) + search(i + 1, right) + sum[right + 1] - sum[left]);
        }
        return dp[left][right];
    }

    private static int parseInt(String input) {
        return Integer.parseInt(input);
    }
}
