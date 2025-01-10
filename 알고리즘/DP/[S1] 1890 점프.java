import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][N];
        long[][] dp = new long[N][N];

        StringTokenizer st;
        dp[0][0] = 1;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 0) {
                    continue;
                }
                int right = j + arr[i][j];
                int down = i + arr[i][j];
                if (right < N) {
                    dp[i][right] += dp[i][j];
                }
                if (down < N) {
                    dp[down][j] += dp[i][j];
                }
            }
        }

        System.out.print(dp[N - 1][N - 1]);
    }
}
