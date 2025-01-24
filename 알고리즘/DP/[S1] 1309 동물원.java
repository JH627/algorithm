import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	static final int MOD = 9901;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		long[] dp = new long[N + 3];
		dp[1] = 3;
		dp[2] = 7;
		for (int i = 3; i < N + 3; i++) {
			dp[i] = (2 * dp[i - 1] + dp[i - 2]) % MOD;
		}
		
		System.out.print(dp[N]);

	}

}
