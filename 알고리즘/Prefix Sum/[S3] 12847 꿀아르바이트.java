import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12847_꿀아르바이트 {

	static BufferedReader br;
	static StringTokenizer st;

	static int dayCount, workCount;
	static long[] money;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(findMaxMoney());
	}

	static long findMaxMoney() {
		long max = money[workCount];

		for (int day = workCount; day <= dayCount; day++) {
			max = Math.max(max, money[day] - money[day - workCount]);
		}

		return max;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		dayCount = Integer.parseInt(st.nextToken());
		workCount = Integer.parseInt(st.nextToken());

		money = new long[dayCount + 1];
		st = new StringTokenizer(br.readLine());
		for (int day = 1; day < dayCount + 1; day++) {
			money[day] = money[day - 1] + Long.parseLong(st.nextToken());
		}
	}
}
