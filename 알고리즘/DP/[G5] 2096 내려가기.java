import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static final int[] dx = {-1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        int[][] dp = new int[N + 1][3];
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                dp[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int[][] min = new int[N + 1][3];
        int[][] max = new int[N + 1][3];
        for (int i = 1; i < N + 1; i++) {
            max[i][0] += Math.max(max[i - 1][0], max[i - 1][1]) + dp[i][0];
            max[i][1] += Math.max(Math.max(max[i - 1][0], max[i - 1][1]), max[i - 1][2]) + dp[i][1];
            max[i][2] += Math.max(max[i - 1][1], max[i - 1][2]) + dp[i][2];

            min[i][0] += Math.min(min[i - 1][0], min[i - 1][1]) + dp[i][0];
            min[i][1] += Math.min(Math.min(min[i - 1][0], min[i - 1][1]), min[i - 1][2]) + dp[i][1];
            min[i][2] += Math.min(min[i - 1][1], min[i - 1][2]) + dp[i][2];
        }

        int max_val = Integer.MIN_VALUE;
        int min_val = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            max_val = Math.max(max_val, max[N][i]);
            min_val = Math.min(min_val, min[N][i]);
        }

        System.out.printf("%d %d", max_val, min_val);
    }
}
