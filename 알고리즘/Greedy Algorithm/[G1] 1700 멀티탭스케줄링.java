import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1700_멀티탭스케줄링 {

	static BufferedReader br;
	static StringTokenizer st;

	static int holeCount, orderCount;
	static int[] order;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getMinChangeCount());
	}

	static int getMinChangeCount() {
		boolean[] used = new boolean[orderCount + 1];

		int currentUsedHoleCount = 0;
		int changeCount = 0;
		for (int now = 0; now < orderCount; now++) {
			int nowDevice = order[now];

			if (used[nowDevice]) {
				continue;
			}

			if (currentUsedHoleCount < holeCount) {
				used[nowDevice] = true;
				currentUsedHoleCount++;
				continue;
			}

			int[] nextUseTime = new int[orderCount + 1];
			Arrays.fill(nextUseTime, Integer.MAX_VALUE);

			int needCheck = currentUsedHoleCount;
			for (int next = now + 1; next < orderCount && needCheck > 0; next++) {
				int nextDevice = order[next];
				if (used[nextDevice] && nextUseTime[nextDevice] == Integer.MAX_VALUE) {
					nextUseTime[nextDevice] = next;
					needCheck--;
				}
			}

			int unplugDevice = -1;
			int farthest = -1;
			for (int next = 1; next <= orderCount; next++) {
				if (!used[next]) {
					continue;
				}

				if (nextUseTime[next] > farthest) {
					farthest = nextUseTime[next];
					unplugDevice = next;
				}
			}

			used[unplugDevice] = false;
			used[nowDevice] = true;
			changeCount++;
		}

		return changeCount;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());

		holeCount = Integer.parseInt(st.nextToken());
		orderCount = Integer.parseInt(st.nextToken());

		order = new int[orderCount];
		st = new StringTokenizer(br.readLine());
		for (int element = 0; element < orderCount; element++) {
			order[element] = Integer.parseInt(st.nextToken());
		}
	}
}
