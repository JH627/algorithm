import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2473_세용액 {

	static BufferedReader br;
	static StringTokenizer st;

	static int elementCount;
	static long[] value, ans;
	static long min;

	public static void main(String[] args) throws IOException {
		init();

		getMinDiff();

		System.out.printf("%d %d %d", ans[0], ans[1], ans[2]);
	}

	static void getMinDiff() {
		min = Long.MAX_VALUE;
		for (int l = 0; l < elementCount - 2; l++) {
			for (int r = l + 1; r < elementCount - 1 && min != 0; r++) {
				long sum = value[l] + value[r];

				int left = r + 1;
				int right = elementCount - 1;

				while (left <= right) {
					int mid = (left + right) / 2;
					long total = sum + value[mid];

					if (Math.abs(total) < min) {
						min = Math.abs(total);
						ans[0] = value[l];
						ans[1] = value[r];
						ans[2] = value[mid];
					}

					if (total < 0) {
						left = mid + 1;
					}
					else {
						right = mid - 1;
					}
				}
			}
		}
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		elementCount = Integer.parseInt(br.readLine());

		value = new long[elementCount];
		st = new StringTokenizer(br.readLine());
		for (int element = 0; element < elementCount; element++) {
			value[element] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(value);

		ans = new long[3];
	}

}
