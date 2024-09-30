import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int C, N;
    static int MIN = Integer.MAX_VALUE;
    static int[] cost, customer, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        cost = new int[N];
        customer = new int[N];

        dp = new int[1100];
        Arrays.fill(dp, Integer.MAX_VALUE);

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            cost[n] = Integer.parseInt(st.nextToken());
            customer[n] = Integer.parseInt(st.nextToken());
        }

        knapsack(0, 0, 0);

        System.out.print(MIN);
    }

    static void knapsack(int idx, int sum, int cnt) {
        if (sum >= C) {
            MIN = Math.min(cnt, MIN);
            return;
        }
        if (idx == N) {
            return;
        }

        for (int i = 1; i < 1100; i++) {
            int next = sum + customer[idx] * i;
            if (dp[next] > cnt + cost[idx] * i) {
                dp[next] = cnt + cost[idx] * i;
                knapsack(idx + 1, next, cnt + cost[idx] * i);
            }
            if (sum + customer[idx] * i >= C) {
                break;
            }
        }
        knapsack(idx + 1, sum, cnt);
    }
}
