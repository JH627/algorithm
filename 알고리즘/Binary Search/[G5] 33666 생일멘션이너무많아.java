import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_33666_생일멘션이너무많아 {

	static int manCount, messageCount;
	static long[] count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		manCount = Integer.parseInt(st.nextToken());
		messageCount = Integer.parseInt(st.nextToken());

		count = new long[manCount];
		st = new StringTokenizer(br.readLine());

		long cnt = 0, sum = 0;
		for (int index = 0; index < manCount; index++) {
			count[index] = Integer.parseInt(st.nextToken());
			if (count[index] >= 2) {
				cnt++;
				sum += count[index];
			}
		}

		Arrays.sort(count);

		double avg = (cnt > 0) ? (sum / (double) cnt) : 0;

		int upperBoundIndex = findUpperBound(avg);
		for (int index = 0; index < upperBoundIndex; index++) {
			if (count[index] > messageCount) {
				System.out.print(-1);
				return;
			}
		}

		long[] appearCount = new long[messageCount];
		Arrays.fill(appearCount, manCount);

		int manIndex = 0;
		for (int messageIndex = 0; messageIndex < messageCount; messageIndex++) {
			while (manIndex < upperBoundIndex && count[manIndex] < messageIndex + 1) {
				manIndex++;
			}

			if (messageIndex == 0) {
				appearCount[messageIndex] = manCount;
			} else {
				appearCount[messageIndex] = (manIndex < upperBoundIndex ? (upperBoundIndex - manIndex) : 0);
			}
		}

		appearCount[0] = manCount;

		StringBuilder sb = new StringBuilder();
		for (long l : appearCount) {
			sb.append(l).append(" ");
		}
		System.out.print(sb.toString().trim());
	}

	public static int findUpperBound(double value) {
		int l = 0, r = count.length;

		while (l < r) {
			int mid = l + (r - l) / 2;
			if (count[mid] > value) {
				r = mid;
			} else {
				l = mid + 1;
			}
		}

		return l;
	}
}
