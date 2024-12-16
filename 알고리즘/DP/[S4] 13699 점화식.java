import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long[] dp = new long[N + 2];
        dp[0] = 1;
        dp[1] = 1;

        for (int n = 2; n < N + 2; n++) {
            for (int i = 0; i < n; i++) {
                dp[n] += (dp[i] * dp[n - i - 1]);
            }
        }

        System.out.print(dp[N]);
    }
}
