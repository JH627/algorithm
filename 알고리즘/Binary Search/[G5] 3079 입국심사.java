import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_3079_입국심사 {

	static BufferedReader br;
	static StringTokenizer st;

	static int tableCount, manCount;
	static int[] time;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(findMinTime());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		tableCount = Integer.parseInt(st.nextToken());
		manCount = Integer.parseInt(st.nextToken());

		time = new int[tableCount];
		for (int table = 0; table < tableCount; table++) {
			time[table] = Integer.parseInt(br.readLine());
		}
	}

	static long findMinTime() {
		Arrays.sort(time);

		long l = 0;
		long r = (long)manCount * time[tableCount - 1];

		while (l <= r) {
			long m = (l + r) / 2;

			if (isPossible(m)) {
				r = m - 1;
			}
			else {
				l = m + 1;
			}
		}

		return l;
	}

	static boolean isPossible(long limit) {
		long sum = 0;

		for (int table = 0; table < tableCount; table++) {
			sum += limit / time[table];
			if (sum >= manCount) {
				return true;
			}
		}

		return false;
	}
}
