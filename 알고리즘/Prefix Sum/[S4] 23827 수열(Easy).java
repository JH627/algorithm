import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_23827_수열(Easy) {

	static BufferedReader br;
	static StringTokenizer st;

	static final int MOD = 1000000007;

	static int elementCount;
	static long[] elements, sum;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getSum());
	}

	static long getSum() {
		long answer = 0;

		for (int element = 0; element < elementCount - 1; element++) {
			answer += (elements[element] * sum[element + 1]) % MOD;
			answer %= MOD;
		}

		return answer;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		elementCount = Integer.parseInt(br.readLine());
		elements = new long[elementCount];
		sum = new long[elementCount + 1];

		st = new StringTokenizer(br.readLine());
		for (int element = 0; element < elementCount; element++) {
			elements[element] = Integer.parseInt(st.nextToken());
		}

		for (int index = elementCount - 1; index >= 0; index--) {
			sum[index] = (sum[index + 1] + elements[index]) % MOD;
		}
	}
}
