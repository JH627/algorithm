import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_9733_꿀벌 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static final String[] works = {"Re", "Pt", "Cc", "Ea", "Tb", "Cm", "Ex"};
	static HashMap<String, Integer> workCount;
	static int totalWork = 0;

	public static void main(String[] args) throws IOException {
		init();
		getWorks();
		printResult();
	}

	static void getWorks() throws IOException {
		String line;
		while ((line = br.readLine()) != null) {
			st = new StringTokenizer(line);

			while (st.hasMoreTokens()) {
				String now = st.nextToken();
				workCount.put(now, workCount.getOrDefault(now, 0) + 1);
				totalWork++;
			}
		}
	}

	static void printResult() {
		sb = new StringBuilder();

		for (String work : works) {
			sb.append(String.format("%s %d %.2f", work, workCount.get(work), workCount.get(work) / (double) totalWork)).append('\n');
		}
		sb.append("Total ").append(totalWork).append(" 1.00").append('\n');

		System.out.print(sb);
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		workCount = new HashMap<>();
		for (String work : works) {
			workCount.put(work, 0);
		}

		totalWork = 0;
	}
}
