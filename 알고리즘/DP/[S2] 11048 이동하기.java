import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] dp, arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N + 1][M + 1];
        dp = new int[N + 1][M + 1];

        for (int n = 1; n < N + 1; n++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 1; m < M + 1; m++) {
                arr[n][m] = Integer.parseInt(st.nextToken());
            }
        }

        for (int n = 1; n < N + 1; n++) {
            for (int m = 1; m < M + 1; m++) {
                dp[n][m] = Math.max(arr[n][m] + dp[n - 1][m], arr[n][m] + dp[n][m - 1]);
            }
        }

        System.out.print(dp[N][M]);
    }
}
