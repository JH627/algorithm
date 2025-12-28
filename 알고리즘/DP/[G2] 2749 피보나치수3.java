import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2749_피보나치수3 {

	static BufferedReader br;

	static final int MOD = 1000000;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		long n = Long.parseLong(br.readLine());
		n %= 15 * 100000;

		long[] fibo = new long[(int)n + 1];
		fibo[0] = 0;
		fibo[1] = 1;
		for (int num = 2; num <= n; num++) {
			fibo[num] = (fibo[num - 1] + fibo[num - 2]) % MOD;
		}

		System.out.print(fibo[(int)n]);
	}
}
