import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class BOJ_1793_타일링 {

	static BufferedReader br;
	static StringBuilder sb;

	static BigInteger[] dp;

	public static void main(String[] args) throws IOException {
		init();
		pringAnswer();
	}

	static void init() {
		dp = new BigInteger[251];
		dp[0] = new BigInteger("1");
		dp[1] = new BigInteger("1");
		dp[2] = new BigInteger("3");

		for (int i = 3; i < 251; i++) {
			dp[i] = dp[i - 2].multiply(new BigInteger("2"));
			dp[i] = dp[i].add(dp[i - 1]);
		}
	}

	static void pringAnswer() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		String line = null;
		while ((line = br.readLine()) != null) {
			sb.append(dp[Integer.parseInt(line)]).append("\n");
		}

		System.out.print(sb);
	}
}
