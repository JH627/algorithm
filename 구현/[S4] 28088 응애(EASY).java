import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_28088_응애(EASY) {

	static BufferedReader br;
	static StringTokenizer st;

	static int userCount, firstUserCount, timeLimit;
	static boolean[] now, next;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getCount());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		userCount = Integer.parseInt(st.nextToken());
		firstUserCount = Integer.parseInt(st.nextToken());
		timeLimit = Integer.parseInt(st.nextToken());
		now = new boolean[userCount];
		next = new boolean[userCount];

		for (int user = 0; user < firstUserCount; user++) {
			int index = Integer.parseInt(br.readLine());
			now[index] = true;
		}
	}

	static int getCount() {
		for (int time = 0; time < timeLimit; time++) {
			Arrays.fill(next, false);
			
			for (int index = 0; index < userCount; index++) {
				boolean left = now[(index - 1 + userCount) % userCount];
				boolean right = now[(index + 1) % userCount];

				next[index] = left ^ right;
			}

			boolean[] temp = now;
			now = next;
			next = temp;
		}

		int count = 0;
		for (int user = 0; user < userCount; user++) {
			if (now[user]) {
				count++;
			}
		}

		return count;
	}

}
