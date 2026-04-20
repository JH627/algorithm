import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1103_게임 {

	static BufferedReader br;
	static StringTokenizer st;

	static final int[] ADD_ROW = {-1, 0, 1, 0};
	static final int[] ADD_COL = {0, 1, 0, -1};
	static final char HOLE = 'H';

	static int rowSize, colSize;
	static char[][] map;

	static int[][] maxDistance;
	static boolean[][] visit;
	static boolean infinite;

	public static void main(String[] args) throws IOException {
		init();
		int maxMoveCount = getMaxMoveCount(0, 0);
		System.out.print(infinite ? -1 : maxMoveCount);
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

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

		maxDistance = new int[rowSize][colSize];
		visit = new boolean[rowSize][colSize];

		infinite = false;
	}

	static int getMaxMoveCount(int row, int col) {
		if (row < 0 || row >= rowSize || col < 0 || col >= colSize) {
			return 0;
		}
		if (map[row][col] == HOLE) {
			return 0;
		}

		if (visit[row][col]) {
			infinite = true;
			return 0;
		}

		if (maxDistance[row][col] != 0) {
			return maxDistance[row][col];
		}

		visit[row][col] = true;

		int move = map[row][col] - '0';
		int max = 0;

		for (int deltaIndex = 0; deltaIndex < 4; deltaIndex++) {
			int newRow = row + ADD_ROW[deltaIndex] * move;
			int newCol = col + ADD_COL[deltaIndex] * move;

			max = Math.max(max, getMaxMoveCount(newRow, newCol) + 1);

			if (infinite) {
				return 0;
			}
		}

		visit[row][col] = false;
		maxDistance[row][col] = max;

		return max;
	}
}
