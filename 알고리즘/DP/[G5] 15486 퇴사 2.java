import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] time, cost, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        time = new int[N + 2];
        cost = new int[N + 2];
        dp = new int[N + 2];

        StringTokenizer st;
        for (int n = 1; n < N + 1; n++) {
            st = new StringTokenizer(br.readLine());
            time[n] = Integer.parseInt(st.nextToken());
            cost[n] = Integer.parseInt(st.nextToken());
        }

        int max = 0;
        for (int n = 1; n < N + 2; n++) {
            max = Math.max(max, dp[n]);
            int finishDay = time[n] + n;
            if (finishDay < N + 2) {
                dp[finishDay] = Math.max(max + cost[n], dp[finishDay]);
            }
        }

        System.out.print(max);
    }
}
