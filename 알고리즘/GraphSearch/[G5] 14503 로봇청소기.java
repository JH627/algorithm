import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14503_로봇청소기 {

	static BufferedReader br;
	static StringTokenizer st;

	static final int[] ADD_ROW = {-1, 0, 1, 0};
	static final int[] ADD_COL = {0, 1, 0, -1};

	static int rowSize, colSize;
	static int[][] map;

	static int curRow, curCol, curDirection;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(findClearCount());
	}

	static int findClearCount() {
		int clearCount = 0;

		while (true) {
			if (map[curRow][curCol] == 0) {
				map[curRow][curCol] = 2;
				clearCount++;
			}

			boolean found = false;
			for (int deltaIndex = 0; deltaIndex < 4; deltaIndex++) {
				curDirection = (curDirection + 3) % 4;
				int newRow = curRow + ADD_ROW[curDirection];
				int newCol = curCol + ADD_COL[curDirection];

				if (newRow >= 0 && newRow < rowSize && newCol >= 0 && newCol < colSize && map[newRow][newCol] == 0) {
					curRow = newRow;
					curCol = newCol;
					found = true;
					break;
				}
			}

			if (found) {
				continue;
			}

			int backDirection = (curDirection + 2) % 4;
			int backRow = curRow + ADD_ROW[backDirection];
			int backCol = curCol + ADD_COL[backDirection];

			if (backRow < 0 || backRow >= rowSize || backCol < 0 || backCol >= colSize || map[backRow][backCol] == 1) {
				break;
			}

			curRow = backRow;
			curCol = backCol;
		}

		return clearCount;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		curRow = Integer.parseInt(st.nextToken());
		curCol = Integer.parseInt(st.nextToken());
		curDirection = Integer.parseInt(st.nextToken());

		map = new int[rowSize][colSize];
		for (int row = 0; row < rowSize; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < colSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
