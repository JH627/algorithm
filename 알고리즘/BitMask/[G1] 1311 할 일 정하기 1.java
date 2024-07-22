import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] arr, dp;
    static final int INF = 2000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        dp = new int[N][1 << N];
        for (int[] d : dp) {
            Arrays.fill(d, INF);
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            dp[0][1 << i] = arr[0][i];
        }
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < (1 << N); j++) {
                if (dp[i - 1][j] == INF) {
                    continue;
                }
                for (int k = 0; k < N; k++) {
                    if ((j & (1 << k)) != 0) {
                        continue;
                    }
                    dp[i][j | (1 << k)] = Math.min(dp[i][j | (1 << k)], dp[i - 1][j] + arr[i][k]);
                }
            }
        }

        System.out.println(dp[N - 1][(1 << N) - 1]);
    }
}
