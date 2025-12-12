import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13702_이상한술집 {

	static BufferedReader br;
	static StringTokenizer st;

	static int kettleCount, friendCount;
	static int[] capacity;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(findMaxCapacity());
	}

	static int findMaxCapacity() {
		int l = 1;
		int r = Integer.MAX_VALUE;

		while (l <= r) {
			int m = l + (r - l) / 2;

			int sum = 0;
			for (int kettle = 0; kettle < kettleCount; kettle++) {
				sum += capacity[kettle] / m;
			}

			if (sum >= friendCount) {
				l = m + 1;
			}
			else {
				r = m - 1;
			}
		}

		return r;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		kettleCount = Integer.parseInt(st.nextToken());
		friendCount = Integer.parseInt(st.nextToken());

		capacity = new int[kettleCount];
		for (int kettle = 0; kettle < kettleCount; kettle++) {
			capacity[kettle] = Integer.parseInt(br.readLine());
		}
	}
}
