import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2508_사탕박사고창영 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int rowSize, colSize;
	static char[][] map;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 0; testCase < TC; testCase++) {
			init();
			sb.append(getCount()).append("\n");
		}

		System.out.print(sb);
	}

	static void init() throws IOException {
		br.readLine();

		st = new StringTokenizer(br.readLine());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());

		map = new char[rowSize][colSize];
		for (int row = 0; row < rowSize; row++) {
			char[] line = br.readLine().toCharArray();
			for (int col = 0; col < colSize; col++) {
				map[row][col] = line[col];
			}
		}
	}

	static int getCount() {
		int count = 0;

		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				if (map[row][col] == 'o') {
					// 가로
					if (col > 0 && col < colSize - 1) {
						if (map[row][col - 1] == '>' && map[row][col + 1] == '<') {
							count++;
						}
					}
					// 세로
					if (row > 0 && row < rowSize - 1) {
						if (map[row - 1][col] == 'v' && map[row + 1][col] == '^') {
							count++;
						}
					}
				}
			}
		}

		return count;
	}
}
