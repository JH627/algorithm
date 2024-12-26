import java.io.*;
import java.util.*;

public class Main {

    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[][] dp = new int[N + 1][N + 1];

        dp[1][1] = 1;
        for (int i = 2; i < N + 1; i++) {
            for (int j = 1; j < i + 1; j++) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }

        System.out.print(dp[N][K]);
    }
}