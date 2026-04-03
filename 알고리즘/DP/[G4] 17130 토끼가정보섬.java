import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17130_토끼가정보섬 {

	static BufferedReader br;
	static StringTokenizer st;

	static final int[] ADD_ROW = {1, 0, -1};
	static final int[] ADD_COL = {1, 1, 1};

	static final char EMPTY = '.';
	static final char WALL = '#';
	static final char RABBIT = 'R';
	static final char CARROT = 'C';
	static final char DOOR = 'O';

	static int rowSize, colSize;
	static char[][] map;
	static int rabbitRow, rabbitCol;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getMaxCarrotCount());
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
				if (map[row][col] == RABBIT) {
					rabbitRow = row;
					rabbitCol = col;
				}
			}
		}
	}

	static int getMaxCarrotCount() {
		int[][] carrotCount = new int[rowSize][colSize];

		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				carrotCount[row][col] = -1;
			}
		}

		carrotCount[rabbitRow][rabbitCol] = 0;

		int maxCarrotCount = -1;
		for (int col = rabbitCol; col < colSize - 1; col++) {
			for (int row = 0; row < rowSize; row++) {
				if (carrotCount[row][col] == -1) {
					continue;
				}
				if (map[row][col] == WALL) {
					continue;
				}

				for (int deltaIndex = 0; deltaIndex < 3; deltaIndex++) {
					int nextRow = row + ADD_ROW[deltaIndex];
					int nextCol = col + ADD_COL[deltaIndex];

					if (nextRow < 0 || nextRow >= rowSize || nextCol < 0 || nextCol >= colSize) {
						continue;
					}
					if (map[nextRow][nextCol] == WALL) {
						continue;
					}

					int nextCarrotCount = carrotCount[row][col];
					if (map[nextRow][nextCol] == CARROT) {
						nextCarrotCount++;
					}

					if (map[nextRow][nextCol] == DOOR) {
						maxCarrotCount = Math.max(maxCarrotCount, nextCarrotCount);
					}

					carrotCount[nextRow][nextCol] = Math.max(carrotCount[nextRow][nextCol], nextCarrotCount);
				}
			}
		}

		return maxCarrotCount;
	}
}
