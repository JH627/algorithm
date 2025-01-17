import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, K;
	static long[][] dp;
	static final long MOD = 1000000000;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		dp = new long[N + 1][K + 1];
		Arrays.fill(dp[0], 1);
		for (int n = 1; n < N + 1; n++) {
			dp[n][0] = 1;
			for (int k = 1; k < K + 1; k++) {
				dp[n][k] = (dp[n - 1][k] + dp[n][k - 1]) % MOD;
			}
		}
		
		System.out.print(dp[N][K - 1]);
	}

}
