import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11116_교통량 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int recordCount;
	static int[] left, right;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 0; testCase < TC; testCase++) {
			init();
			sb.append(findCount()).append("\n");
		}

		System.out.print(sb);
	}

	static int findCount() {
		int count = 0;
		for (int record = 0; record < recordCount; record++) {
			int l = left[record];
			int time = Arrays.binarySearch(right, 0, recordCount, l + 1000);
			if (time >= 0) {
				count++;
			}
		}

		return count / 2;
	}

	static void init() throws IOException {
		recordCount = Integer.parseInt(br.readLine());

		left = new int[recordCount];
		right = new int[recordCount];

		st = new StringTokenizer(br.readLine());
		for (int index = 0; index < recordCount; index++) {
			left[index] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int index = 0; index < recordCount; index++) {
			right[index] = Integer.parseInt(st.nextToken());
		}
	}
}
