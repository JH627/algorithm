import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10159_저울 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int elementCount, queryCount;
	static int[][] status;

	public static void main(String[] args) throws IOException {
		init();
		findStatus();
		printResult();
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		elementCount = Integer.parseInt(br.readLine());
		queryCount = Integer.parseInt(br.readLine());

		status = new int[elementCount][elementCount];
		for (int query = 0; query < queryCount; query++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			status[a][b] = 1;
			status[b][a] = -1;
		}
	}

	static void findStatus() {
		for (int mid = 0; mid < elementCount; mid++) {
			for (int start = 0; start < elementCount; start++) {
				for (int end = 0; end < elementCount; end++) {
					if (status[start][mid] == 1 && status[mid][end] == 1) {
						status[start][end] = 1;
					}
					if (status[start][mid] == -1 && status[mid][end] == -1) {
						status[start][end] = -1;
					}
				}
			}
		}
	}

	static void printResult() {
		sb = new StringBuilder();
		for (int element = 0; element < elementCount; element++) {
			int count = 1;
			for (int other = 0; other < elementCount; other++) {
				if (status[element][other] != 0) {
					count++;
				}
			}
			sb.append(elementCount - count).append("\n");
		}
		System.out.print(sb);
	}
}
