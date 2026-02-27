import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_27111_출입기록 {

	static BufferedReader br;
	static StringTokenizer st;

	static int recordCount;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getMinMissingCount());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		recordCount = Integer.parseInt(br.readLine());
	}

	static long getMinMissingCount() throws IOException {
		boolean[] inBase = new boolean[200001];

		long missing = 0;
		int insideCount = 0;
		for (int record = 0; record < recordCount; record++) {
			st = new StringTokenizer(br.readLine());
			int id = Integer.parseInt(st.nextToken());
			int operation = Integer.parseInt(st.nextToken());

			if (operation == 1) {
				if (inBase[id]) {
					missing++;
				}
				else {
					inBase[id] = true;
					insideCount++;
				}
			}
			else {
				if (!inBase[id]) {
					missing++;
				}
				else {
					inBase[id] = false;
					insideCount--;
				}
			}
		}

		missing += insideCount;
		return missing;
	}
}
