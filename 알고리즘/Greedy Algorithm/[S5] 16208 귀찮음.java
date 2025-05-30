import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_16208_귀찮음 {

	static BufferedReader br;
	static StringTokenizer st;

	static int elementCount, sum;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		init();

		System.out.println(getMin());
	}

	static long getMin() {
		long ret = 0;
		for (int elementIndex = 0; elementIndex < elementCount - 1; elementIndex++) {
			sum -= arr[elementIndex];
			ret += arr[elementIndex] * (sum);
		}

		return ret;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		elementCount = Integer.parseInt(br.readLine());

		arr = new int[elementCount];
		sum = 0;
		st = new StringTokenizer(br.readLine());
		for (int elementIndex = 0; elementIndex < elementCount; elementIndex++) {
			arr[elementIndex] = Integer.parseInt(st.nextToken());
			sum += arr[elementIndex];
		}

		Arrays.sort(arr);
	}

}
