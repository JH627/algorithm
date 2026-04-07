import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_23301_스터디시간정하기2 {

	static BufferedReader br;
	static StringTokenizer st;

	static int manCount, studyTime;
	static int[] available;

	public static void main(String[] args) throws IOException {
		init();
		findMaxHappy();
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		manCount = Integer.parseInt(st.nextToken());
		studyTime = Integer.parseInt(st.nextToken());

		available = new int[1001];
		for (int man = 0; man < manCount; man++) {
			int timeCount = Integer.parseInt(br.readLine());

			for (int time = 0; time < timeCount; time++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());

				for (int t = start; t < end; t++) {
					available[t]++;
				}
			}
		}
	}

	static void findMaxHappy() {
		int currentSum = 0;

		for (int time = 0; time < studyTime; time++) {
			currentSum += available[time];
		}

		int maxSum = currentSum;
		int bestStart = 0;

		for (int time = 1; time + studyTime < 1001; time++) {
			currentSum -= available[time - 1];
			currentSum += available[time + studyTime - 1];

			if (currentSum > maxSum) {
				maxSum = currentSum;
				bestStart = time;
			}
		}

		System.out.printf("%d %d", bestStart, bestStart + studyTime);
	}
}
