import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_31674_특별한기술력 {

	static BufferedReader br;
	static StringTokenizer st;

	static final long MOD = 1000000007;

	static int elementCount;
	static long[] elements;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(findMaxHeight());
	}

	static long findMaxHeight() {
		long sum = 0;
		for (int index = elementCount - 1; index >= 0; index--) {
			sum = (sum * 2 + elements[index]) % MOD;
		}
		return sum;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		elementCount = Integer.parseInt(br.readLine());
		elements = new long[elementCount];
		st = new StringTokenizer(br.readLine());
		for (int element = 0; element < elementCount; element++) {
			elements[element] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(elements);
	}
}
