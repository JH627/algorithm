import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16929_TwoDots {

	static BufferedReader br;
	static StringTokenizer st;

	static final int[] ADD_ROW = {-1, 0, 1, 0};
	static final int[] ADD_COL = {0, -1, 0, 1};

	static int rowSize, colSize;
	static char[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(hasCycle() ? "Yes" : "No");
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

		visited = new boolean[rowSize][colSize];
	}

	static boolean hasCycle() {
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				if (checkCycle(row, col, -1, -1)) {
					return true;
				}
			}
		}
		return false;
	}

	static boolean checkCycle(int row, int col, int prevRow, int prevCol) {
		visited[row][col] = true;
		boolean cycle = false;
		for (int deltaIndex = 0; deltaIndex < 4; deltaIndex++) {
			int newRow = row + ADD_ROW[deltaIndex];
			int newCol = col + ADD_COL[deltaIndex];

			if (newRow < 0 || newRow >= rowSize || newCol < 0 || newCol >= colSize) {
				continue;
			}

			if (newRow == prevRow && newCol == prevCol) {
				continue;
			}

			if (visited[newRow][newCol]) {
				return true;
			}

			if (map[newRow][newCol] == map[row][col]) {
				if (checkCycle(newRow, newCol, row, col)) {
					cycle = true;
					break;
				}
			}
		}

		visited[row][col] = false;
		return cycle;
	}
}
