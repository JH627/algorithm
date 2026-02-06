import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_3687_성냥개비 {

	static BufferedReader br;
	static StringBuilder sb;

	static long[] minNumber;
	static String[] maxNumber;

	public static void main(String[] args) throws IOException {
		init();

		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 0; testCase < TC; testCase++) {
			int number = Integer.parseInt(br.readLine());
			sb.append(minNumber[number]).append(" ").append(maxNumber[number]).append("\n");
		}

		System.out.print(sb);
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		minNumber = new long[101];
		maxNumber = new String[101];

		Arrays.fill(minNumber, Long.MAX_VALUE);
		minNumber[2] = 1;
		minNumber[3] = 7;
		minNumber[4] = 4;
		minNumber[5] = 2;
		minNumber[6] = 6;
		minNumber[7] = 8;
		minNumber[8] = 10;

		long[] minSet = {1, 7, 4, 2, 0, 8};
		for (int i = 9; i < 101; i++) {
			for (int j = 2; j < 2 + 6; j++) {
				minNumber[i] = Math.min((minNumber[i - j] * 10) + minSet[j - 2], minNumber[i]);
			}
		}

		maxNumber[2] = "1";
		maxNumber[3] = "7";
		for (int i = 4; i < 101; i++) {
			if (i % 2 == 0) {
				maxNumber[i] = "1" + maxNumber[i - 2];
			}
			else {
				maxNumber[i] = "7" + maxNumber[i - 3];
			}
		}
	}
}
