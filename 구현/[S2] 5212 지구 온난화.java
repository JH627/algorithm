import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_5212_지구온난화 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static final int[] ADD_ROW = {-1, 0, 1, 0};
	static final int[] ADD_COL = {0, -1, 0, 1};

	static int rowSize, colSize;
	static char[][] map, afterMap;
	static int minRow, maxRow, minCol, maxCol;

	public static void main(String[] args) throws IOException {
		init();
		findAfterMap();
		printMap();
	}

	static void findAfterMap() {
		afterMap = new char[rowSize][colSize];

		minRow = minCol = Integer.MAX_VALUE;
		maxRow = maxCol = Integer.MIN_VALUE;

		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				if (map[row][col] == '.') {
					afterMap[row][col] = '.';
					continue;
				}
				int count = 0;
				for (int deltaIndex = 0; deltaIndex < 4; deltaIndex++) {
					int newRow = row + ADD_ROW[deltaIndex];
					int newCol = col + ADD_COL[deltaIndex];
					if (newRow < 0 || newRow >= rowSize || newCol < 0 || newCol >= colSize) {
						count++;
						continue;
					}
					if (map[newRow][newCol] == '.') {
						count++;
					}
				}

				if (count >= 3) {
					afterMap[row][col] = '.';
				}
				else {
					afterMap[row][col] = 'X';
					minRow = Math.min(minRow, row);
					maxRow = Math.max(maxRow, row);
					minCol = Math.min(minCol, col);
					maxCol = Math.max(maxCol, col);
				}
			}
		}
	}

	static void printMap() {
		sb = new StringBuilder();
		for (int row = minRow; row <= maxRow; row++) {
			for (int col = minCol; col <= maxCol; col++) {
				sb.append(afterMap[row][col]);
			}
			sb.append("\n");
		}
		System.out.print(sb);
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
