import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_33910_합의최소 {

	static BufferedReader br;
	static StringTokenizer st;

	static int numberCount;
	static int[] numbers;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getMinValue());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		numberCount = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		numbers = new int[numberCount];
		for (int number = 0; number < numberCount; number++) {
			numbers[number] = Integer.parseInt(st.nextToken());
		}
	}

	static long getMinValue() {
		long sum = 0;

		int minValue = numbers[numberCount - 1];
		for (int index = numberCount - 1; index >= 0; index--) {
			minValue = Math.min(minValue, numbers[index]);
			sum += minValue;
		}

		return sum;
	}
}
