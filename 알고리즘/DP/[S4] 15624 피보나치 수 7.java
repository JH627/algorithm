import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int n;
	static long[] dp;
	static final long MOD = 1000000007L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		dp = new long[n + 2];

		dp[0] = 0;
		dp[1] = 1;

		for (int i = 2; i < n + 2; i++) {
			dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
		}

		System.out.print(dp[n]);
    }
}
