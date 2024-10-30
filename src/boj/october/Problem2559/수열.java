package src.boj.october.Problem2559;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class 수열 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = parseInt(st.nextToken());
        int k = parseInt(st.nextToken());
        int[] temperatureSum = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            temperatureSum[i] = temperatureSum[i - 1] + parseInt(st.nextToken());
        }
        int maxTemperature = Integer.MIN_VALUE;
        for (int i = 0; i <= n - k; i++) {
            maxTemperature = Math.max(maxTemperature, temperatureSum[i + k] - temperatureSum[i]);
        }
        System.out.println(maxTemperature);
    }

    private static int parseInt(String input) {
        return Integer.parseInt(input);
    }
}
