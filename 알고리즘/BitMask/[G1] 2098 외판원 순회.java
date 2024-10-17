import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] w, dp;
    static int allVisited;
    static final int INF = 17000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        w = new int[N][N];
        dp = new int[N][(1 << N)];
        allVisited = (1 << N) - 1;
        for (int n = 0; n < N; n++) {
            Arrays.fill(dp[n], -1);
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                w[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.print(dfs(0, 1));
    }

    static int dfs(int now, int visited) {
        if (visited == allVisited) {
            return (w[now][0] == 0) ? INF : w[now][0];
        }

        if (dp[now][visited] != -1) {
            return dp[now][visited];
        }
        dp[now][visited] = INF;

        for (int i = 0; i < N; i++) {
            int next = (1 << i);
            if ((visited & next) != 0) {
                continue;
            }
            if (w[now][i] == 0) {
                continue;
            }
            dp[now][visited] = Math.min(dfs(i, visited | next) + w[now][i], dp[now][visited]);
        }
        return dp[now][visited];
    }
}
