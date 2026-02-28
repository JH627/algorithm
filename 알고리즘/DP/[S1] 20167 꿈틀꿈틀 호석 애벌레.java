import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20167_꿈틀꿈틀호석애벌레 {

	static BufferedReader br;
	static StringTokenizer st;

	static int elementCount, limit;
	static int[] elements;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getMaxPower());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		elementCount = Integer.parseInt(st.nextToken());
		limit = Integer.parseInt(st.nextToken());

		elements = new int[elementCount];
		st = new StringTokenizer(br.readLine());
		for (int element = 0; element < elementCount; element++) {
			elements[element] = Integer.parseInt(st.nextToken());
		}
	}

	static long getMaxPower() {
		long[] dp = new long[elementCount + 1];

		long sum = elements[0];
		int l = 0;
		int r = 1;
		while (r <= elementCount) {
			if (sum >= limit) {
				while (sum >= limit) {
					dp[r] = Math.max(dp[l] + (sum - limit), dp[r]);
					sum -= elements[l];
					l++;
				}
			}
			else {
				dp[r] = Math.max(dp[r - 1], dp[r]);

				if (r == elementCount) {
					break;
				}

				sum += elements[r];
				r++;
			}
		}

		return dp[elementCount];
	}
}
