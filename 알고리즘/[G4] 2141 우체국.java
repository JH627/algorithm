import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2141_우체국 {

	static BufferedReader br;
	static StringTokenizer st;

	static class Town implements Comparable<Town> {
		int index;
		long manCount;

		public Town(int index, long manCount) {
			this.index = index;
			this.manCount = manCount;
		}

		@Override
		public int compareTo(Town o) {
			return Long.compare(this.index, o.index);
		}
	}

	static int townCount;
	static long manSum;
	static Town[] towns;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getMinDistance());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		townCount = Integer.parseInt(br.readLine());
		towns = new Town[townCount];
		manSum = 0;
		for (int town = 0; town < townCount; town++) {
			st = new StringTokenizer(br.readLine());
			towns[town] = new Town(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			manSum += towns[town].manCount;
		}
	}

	static int getMinDistance() {
		Arrays.sort(towns);

		long target = (manSum + 1) / 2;
		long sum = 0;
		for (int town = 0; town < townCount; town++) {
			sum += towns[town].manCount;
			if (sum >= target) {
				return towns[town].index;
			}
		}

		return 0;
	}
}
