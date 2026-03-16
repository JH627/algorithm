import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_9024_두수의합 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int numberCount, targetNumber;
	static int[] numbers;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 0; testCase < TC; testCase++) {
			init();
			sb.append(getCount()).append("\n");
		}

		System.out.print(sb);
	}

	static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		numberCount = Integer.parseInt(st.nextToken());
		targetNumber = Integer.parseInt(st.nextToken());

		numbers = new int[numberCount];
		st = new StringTokenizer(br.readLine());
		for (int number = 0; number < numberCount; number++) {
			numbers[number] = Integer.parseInt(st.nextToken());
		}
	}

	static int getCount() {
		Arrays.sort(numbers);

		int count = 0;
		int minDiff = Integer.MAX_VALUE;

		int l = 0;
		int r = numberCount - 1;

		while (l < r) {
			int sum = numbers[l] + numbers[r];
			int diff = Math.abs(sum - targetNumber);

			if (diff < minDiff) {
				minDiff = diff;
				count = 1;
			}
			else if (diff == minDiff) {
				count++;
			}

			if (sum < targetNumber) {
				l++;
			}
			else if (sum > targetNumber) {
				r--;
			}
			else {
				l++;
				r--;
			}
		}

		return count;
	}
}
