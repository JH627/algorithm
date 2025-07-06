import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_15990_123더하기5 {

	static BufferedReader br;
	static StringBuilder sb;

	static final int MOD = 1000000009;

	static long[][] count;

	public static void main(String[] args) throws IOException {
		init();
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 0; testCase < TC; testCase++) {
			int number = Integer.parseInt(br.readLine());
			long ans = (count[number][1] + count[number][2] + count[number][3]) % MOD;
			sb.append(ans).append("\n");
		}
		System.out.print(sb);
	}

	static void init() {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		count = new long[100001][4];
		count[1][1] = 1; count[2][2] = 1; count[3][3] = 1;
		count[3][1] = 1; count[3][2] = 1;

		for (int index = 4; index < 100001; index++) {
			count[index][1] = (count[index - 1][2] + count[index - 1][3]) % MOD;
			count[index][2] = (count[index - 2][1] + count[index - 2][3]) % MOD;
			count[index][3] = (count[index - 3][1] + count[index - 3][2]) % MOD;
		}
	}
}
