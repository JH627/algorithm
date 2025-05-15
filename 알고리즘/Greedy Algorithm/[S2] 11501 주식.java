import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11501_주식 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int dayCount;
	static int[] price;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 0; testCase < TC; testCase++) {
			init();

			sb.append(findMaxValue()).append("\n");
		}

		System.out.print(sb);
	}

	static long findMaxValue() {
		long sum = 0;
		long max = price[dayCount - 1];
		for (int day = dayCount - 2; day >= 0; day--) {
			if (price[day] <= max) {
				sum += max - price[day];
			}
			else {
				max = price[day];
			}
		}

		return sum;
	}

	static void init() throws IOException {
		dayCount = Integer.parseInt(br.readLine());
		price = new int[dayCount];

		st = new StringTokenizer(br.readLine());
		for (int day = 0; day < dayCount; day++) {
			price[day] = Integer.parseInt(st.nextToken());
		}
	}

}
