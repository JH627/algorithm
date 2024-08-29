import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int MAX = 10001;
    static int MIN = Integer.MAX_VALUE;

    static int N, M;
    static int[] m, c;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        m = new int[N];
        c = new int[N];
        dp = new int[N][MAX];

        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            m[n] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            c[n] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < MAX; j++) {
                if (i == 0) {
                    dp[i][c[i]] = m[i];
                }
                else {
                    if (j >= c[i]) {
                        dp[i][j] = Math.max(dp[i - 1][j - c[i]] + m[i], dp[i - 1][j]);
                    }
                    else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }

                if (dp[i][j] >= M) {
                    MIN = Math.min(MIN, j);
                }
            }
        }

        System.out.print(MIN);
    }
}
