import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_11637_인기투표 {

	static BufferedReader br;
	static StringBuilder sb;

	static class People implements Comparable<People> {
		int id, count;

		public People(int id, int count) {
			this.id = id;
			this.count = count;
		}

		@Override
		public int compareTo(People o) {
			return this.count - o.count;
		}
	}

	static int peopleCount;
	static int countSum;
	static People[] people;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 0; testCase < TC; testCase++) {
			init();
			findWinner();
		}
		System.out.print(sb);
	}

	static void findWinner() {
		Arrays.sort(people);

		if (people[peopleCount - 1].count == people[peopleCount - 2].count) {
			sb.append("no winner\n");
			return;
		}
		if (people[peopleCount - 1].count > countSum / 2) {
			sb.append("majority winner ").append(people[peopleCount - 1].id).append("\n");
		}
		else {
			sb.append("minority winner ").append(people[peopleCount - 1].id).append("\n");
		}
	}

	static void init() throws IOException {
		peopleCount = Integer.parseInt(br.readLine());
		people = new People[peopleCount];
		countSum = 0;
		for (int id = 0; id < peopleCount; id++) {
			int count = Integer.parseInt(br.readLine());
			people[id] = new People(id + 1, count);
			countSum += count;
		}
	}
}
