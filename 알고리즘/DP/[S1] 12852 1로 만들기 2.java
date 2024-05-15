import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int X;
    static int[] dp;
    static int[] trace;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        X = Integer.parseInt(st.nextToken());

        dp = new int[X + 1];
        trace = new int[X + 1];

        dp[0] = dp[1] = 0;
        for (int i = 2; i <= X; i++) {
            dp[i] = dp[i - 1] + 1;
            trace[i] = i - 1;

            if (i % 2 == 0 && dp[i] > dp[i / 2] + 1) {
                dp[i] = dp[i / 2] + 1;
                trace[i] = i / 2;
            }

            if (i % 3 == 0 && dp[i] > dp[i / 3] + 1){
                dp[i] = dp[i / 3] + 1;
                trace[i] = i / 3;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(dp[X]).append("\n");

        while (X != 0) {
            sb.append(X).append(" ");
            X = trace[X];
        }

        System.out.println(sb);
    }
}