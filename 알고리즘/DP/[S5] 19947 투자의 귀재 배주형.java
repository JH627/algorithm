import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] dp;
	static int H, Y;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		H = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());

		dp = new int[Y + 1];

		dp[0] = H;
		for (int y = 1; y <= Y; y++) {
			dp[y] = (int)(dp[y - 1] * 1.05);

			if (y >= 3) {
				dp[y] = Math.max((int)(dp[y - 3] * 1.2), dp[y]);
			}
			if (y >= 5) {
				dp[y] = Math.max((int)(dp[y - 5] * 1.35), dp[y]);
			}
		}
		System.out.print(dp[Y]);
	}
}
