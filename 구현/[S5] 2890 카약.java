import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2890_카약 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int rowSize, colSize;
	static char[][] map;
	static int[] rank;

	public static void main(String[] args) throws IOException {
		init();

		getRank();

		for (int team = 1; team < 10; team++) {
			sb.append(rank[team]).append("\n");
		}

		System.out.print(sb);
	}

	static void getRank() {
		int sequence = 1;
		for (int col = colSize - 2; col > 0; col--) {
			int find = 0;
			for (int row = 0; row < rowSize; row++) {
				if (map[row][col] != '.') {
					if (rank[map[row][col] - '0'] == 0) {
						rank[map[row][col] - '0'] = sequence;
						find = 1;
					}
				}
			}
			sequence += find;
		}

	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());

		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		map = new char[rowSize][colSize];

		for (int row = 0; row < rowSize; row++) {
			map[row] = br.readLine().toCharArray();
		}

		rank = new int[10];
	}
}
