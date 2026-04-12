import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_25603_짱해커이동식 {

	static BufferedReader br;
	static StringTokenizer st;

	static int requestCount, limit;
	static int[] cost;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getMinCost());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		requestCount = Integer.parseInt(st.nextToken());
		limit = Integer.parseInt(st.nextToken());

		cost = new int[requestCount];
		st = new StringTokenizer(br.readLine());
		for (int request = 0; request < requestCount;request++) {
			cost[request] = Integer.parseInt(st.nextToken());
		}
	}

	static int getMinCost() {
		int left = 1;
		int right = 1000000000;
		int answer = right;

		while (left <= right) {
			int mid = left + (right - left) / 2;

			if (check(mid)) {
				answer = mid;
				right = mid - 1;
			} 
			else {
				left = mid + 1;
			}
		}

		return answer;
	}

	static boolean check(int maxAllowedCost) {
		int rejectedStreak = 0;

		for (int request = 0; request < requestCount; request++) {
			if (cost[request] <= maxAllowedCost) {
				rejectedStreak = 0;
			} 
			else {
				rejectedStreak++;
				if (rejectedStreak >= limit) {
					return false;
				}
			}
		}

		return true;
	}
}
