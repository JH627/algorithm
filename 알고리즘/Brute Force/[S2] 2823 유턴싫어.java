import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2823_유턴싫어 {

	static BufferedReader br;
	static StringTokenizer st;

	static final int[] ADD_ROW = {-1, 0, 1, 0};
	static final int[] ADD_COL = {0, -1, 0, 1};

	static int rowSize, colSize;
	static char[][] map;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(hasCycle() ? "0" : "1");
	}

	static boolean hasCycle() {
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				if (map[row][col] == '.') {
					if (getEdgeCount(row, col) <= 1) {
						return false;
					}
				}
			}
		}

		return true;
	}

	static int getEdgeCount(int row, int col) {
		int edgeCount = 0;

		for (int deltaIndex = 0; deltaIndex < 4; deltaIndex++) {
			int newRow = row + ADD_ROW[deltaIndex];
			int newCol = col + ADD_COL[deltaIndex];

			if (newRow < 0 || newRow >= rowSize || newCol < 0 || newCol >= colSize) {
				continue;
			}

			if (map[newRow][newCol] == '.') {
				edgeCount++;
			}
		}

		return edgeCount;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());

		map = new char[rowSize][colSize];
		for (int row = 0; row < rowSize; row++) {
			String line = br.readLine();
			for (int col = 0; col < colSize; col++) {
				map[row][col] = line.charAt(col);
			}
		}
	}
}
