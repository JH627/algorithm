import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_3078_좋은친구 {

	static BufferedReader br;
	static StringTokenizer st;

	static class Score {
		String name;
		int ranking;

		public Score(String name, int ranking) {
			this.name = name;
			this.ranking = ranking;
		}
	}

	static int personCount, rankCutLimit;
	static ArrayList<Score>[] persons;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getDuoCount());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		persons = new ArrayList[21];
		for (int nameLength = 0; nameLength < 21; nameLength++) {
			persons[nameLength] = new ArrayList();
		}

		st = new StringTokenizer(br.readLine());
		personCount = Integer.parseInt(st.nextToken());
		rankCutLimit = Integer.parseInt(st.nextToken());

		for (int rank = 0; rank < personCount; rank++) {
			String name = br.readLine();
			int length = name.length();

			persons[length].add(new Score(name, rank));
		}
	}

	static long getDuoCount() {
		long count = 0;
		for (int nameLength = 2; nameLength < 21; nameLength++) {
			if (persons[nameLength].size() <= 1) {
				continue;
			}

			int l = 0;
			for (int r = 0; r < persons[nameLength].size(); r++) {
				while (persons[nameLength].get(r).ranking - persons[nameLength].get(l).ranking > rankCutLimit) {
					l++;
				}

				count += (r - l);
			}
		}

		return count;
	}

}
