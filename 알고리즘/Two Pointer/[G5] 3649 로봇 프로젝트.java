import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_3649_로봇프로젝트 {

	static StringBuilder sb;
	static BufferedReader br;

	static final long NM = 10000000;

	static long width;
	static int elementCount;
	static long[] length;
	static long maxA, maxB;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		while (init()) {
			maxA = maxB = -1;
			if (findMaxBlock()) {
				sb.append("yes ").append(maxA).append(" ").append(maxB).append("\n");
			}
			else {
				sb.append("danger\n");
			}
		}

		System.out.print(sb);
	}

	static boolean findMaxBlock() {
		int l = 0;
		int r = elementCount - 1;

		while (l < r) {
			long sum = length[l] + length[r];
			if (sum == width) {
				if (Math.abs(length[l] - length[r]) >= Math.abs(maxA - maxB)) {
					maxA = length[l];
					maxB = length[r];
				}
				l++;
				r--;
			}
			else if (sum > width) {
				r--;
			}
			else {
				l++;
			}
		}

		return (maxA != -1);
	}

	static boolean init() throws IOException {
		String testCase;
		if ((testCase = br.readLine()) == null) {
			return false;
		}
		width = Integer.parseInt(testCase) * NM;
		elementCount = Integer.parseInt(br.readLine());

		length = new long[elementCount];
		for (int elementIndex = 0; elementIndex < elementCount; elementIndex++) {
			length[elementIndex] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(length);

		return true;
	}
}
