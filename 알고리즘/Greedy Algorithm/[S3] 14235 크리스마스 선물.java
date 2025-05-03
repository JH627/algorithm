import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_14235_크리스마스선물 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int queryCount;

	public static void main(String[] args) throws Exception {
		init();

		getValues();

		System.out.println(sb);
	}

	static void getValues() throws IOException {
		PriorityQueue<Integer> maxValue = new PriorityQueue<>(Comparator.reverseOrder());

		for (int queryIndex = 0; queryIndex < queryCount; queryIndex++) {
			st = new StringTokenizer(br.readLine());

			int itemCount = Integer.parseInt(st.nextToken());
			for (int itemIndex = 0; itemIndex < itemCount; itemIndex++) {
				maxValue.add(Integer.parseInt(st.nextToken()));
			}

			if (itemCount == 0) {
				sb.append(maxValue.isEmpty() ? "-1" : maxValue.poll()).append("\n");
			}
		}
	}

	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		queryCount = Integer.parseInt(br.readLine());
	}
}
