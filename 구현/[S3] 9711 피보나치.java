import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class BOJ_9711_피보나치 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static BigInteger[] fib;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		init();
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= TC; testCase++) {
			st = new StringTokenizer(br.readLine());
			int number = Integer.parseInt(st.nextToken());
			int mod = Integer.parseInt(st.nextToken());
			sb.append("Case #").append(testCase).append(": ").append(fib[number].mod(BigInteger.valueOf(mod))).append("\n");
		}
		System.out.print(sb);
	}

	static void init() {
		fib = new BigInteger[10001];
		fib[0] = new BigInteger("0");
		fib[1] = new BigInteger("1");
		for (int num = 2; num < 10001; num++) {
			fib[num] = fib[num - 1].add(fib[num - 2]);
		}
	}

}
