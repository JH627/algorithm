import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15824_너봄에는캡사이신이맛있단다 {

	static BufferedReader br;
	static StringTokenizer st;

	static long MOD = 1000000007;

	static int elementCount;
	static int[] element;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getSum());
	}

	static long getSum() {
		long sum = 0;
		for (int index = 0; index < elementCount; index++) {
			sum += element[index] * ((power(2, index) - 1) - (power(2, elementCount - 1 - index) - 1)) % MOD;
			sum %= MOD;
		}
		return (sum + MOD) % MOD;
	}

	static long power(long n, int p) {
		if (p == 0) {
			return 1;
		}
		long sqrt = power(n, p / 2);
		if (p % 2 == 1) {
			return ((sqrt * sqrt) % MOD) * n % MOD;
		}
		else {
			return (sqrt * sqrt) % MOD;
		}
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		elementCount = Integer.parseInt(br.readLine());
		element = new int[elementCount];
		st = new StringTokenizer(br.readLine());
		for (int index = 0; index < elementCount; index++) {
			element[index] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(element);
	}
}
