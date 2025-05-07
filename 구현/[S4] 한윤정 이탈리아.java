import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2422_한윤정이탈리아 {

	static BufferedReader br;
	static StringTokenizer st;

	static int elementCount, limitCount;
	static boolean[][] impossible;

	public static void main(String[] args) throws IOException {
		init();

		System.out.print(getCombCount());
	}

	static int getCombCount() {
		int count = 0;

		for (int i = 0; i < elementCount - 2; i++) {
			for (int j = i + 1; j < elementCount - 1; j++) {
				if (impossible[i][j]) {
					continue;
				}
				for (int k = j + 1; k < elementCount; k++) {
					if (impossible[i][k] || impossible[k][j]) {
						continue;
					}
					count++;
				}
			}
		}

		return count;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());

		elementCount = Integer.parseInt(st.nextToken());
		limitCount = Integer.parseInt(st.nextToken());

		impossible = new boolean[elementCount][elementCount];
		for (int line = 0; line < limitCount; line++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			impossible[a][b] = true;
			impossible[b][a] = true;
		}
	}
}
