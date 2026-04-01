import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_26070_곰곰이와학식 {

	static BufferedReader br;
	static StringTokenizer st;

	static long chickenCount, pizzaCount, hamburgerCount;
	static long chickenUserCount, pizzaUserCount, hamburgerUserCount;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getMaxUserCount());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		chickenCount = Long.parseLong(st.nextToken());
		pizzaCount = Long.parseLong(st.nextToken());
		hamburgerCount = Long.parseLong(st.nextToken());

		st = new StringTokenizer(br.readLine());
		chickenUserCount = Long.parseLong(st.nextToken());
		pizzaUserCount = Long.parseLong(st.nextToken());
		hamburgerUserCount = Long.parseLong(st.nextToken());
	}

	static long getMaxUserCount() {
		long[] need = {chickenCount, pizzaCount, hamburgerCount};
		long[] ticket = {chickenUserCount, pizzaUserCount, hamburgerUserCount};

		long count = 0;
		for (int food = 0; food < 3; food++) {
			long eat = Math.min(need[food], ticket[food]);
			count += eat;
			need[food] -= eat;
			ticket[food] -= eat;
		}

		while (true) {
			boolean changed = false;

			for (int food = 0; food < 3; food++) {
				int next = (food + 1) % 3;

				long exchanged = ticket[food] / 3;
				if (exchanged == 0) {
					continue;
				}

				ticket[food] %= 3;
				ticket[next] += exchanged;
				changed = true;

				long eat = Math.min(need[next], ticket[next]);
				count += eat;
				need[next] -= eat;
				ticket[next] -= eat;
			}

			if (!changed) {
				break;
			}
		}

		return count;
	}
}
