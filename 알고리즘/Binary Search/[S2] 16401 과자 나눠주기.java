import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16401_과자나눠주기 {

	static BufferedReader br;
	static StringTokenizer st;

	static int personCount;
	static int snackCount;

	static int[] snackLength;
	static int singleMaxLength;

	public static void main(String[] args) throws Exception {
		init();

		System.out.print(getMaxLength());
	}

	static int getMaxLength() {
		int l = 1;
		int r = singleMaxLength;

		int max = 0;
		while (l <= r) {
			int mid = l + (r - l) / 2;

			if (possible(mid)) {
				max = mid;
				l = mid + 1;
			}
			else {
				r = mid - 1;
			}
		}

		return max;
	}

	static boolean possible(int limitLength) {
		int count = 0;
		for (int snackIndex = 0; snackIndex < snackCount; snackIndex++) {
			count += snackLength[snackIndex] / limitLength;
			if (count >= personCount) {
				return true;
			}
		}
		return false;
	}

	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());

		personCount = Integer.parseInt(st.nextToken());
		snackCount = Integer.parseInt(st.nextToken());

		singleMaxLength = 0;
		snackLength = new int[snackCount];
		st = new StringTokenizer(br.readLine());
		for (int snackIndex = 0; snackIndex < snackCount; snackIndex++) {
			snackLength[snackIndex] = Integer.parseInt(st.nextToken());
			singleMaxLength = Math.max(singleMaxLength, snackLength[snackIndex]);
		}
	}
}
