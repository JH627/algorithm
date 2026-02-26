import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_29733_3차원지뢰찾기 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int rowSize, colSize, height;
	static boolean[][][] map;
	static int[][][] count;

	public static void main(String[] args) throws IOException {
		init();
		checkMap();
		printMap();
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		height = Integer.parseInt(st.nextToken());

		map = new boolean[rowSize][colSize][height];
		count = new int[rowSize][colSize][height];

		for (int h = 0; h < height; h++) {
			for (int row = 0; row < rowSize; row++) {
				char[] line = br.readLine().toCharArray();
				for (int col = 0; col < colSize; col++) {
					if (line[col] == '*') {
						map[row][col][h] = true;
					}
				}
			}
		}
	}

	static void checkMap() {
		for (int h = 0; h < height; h++) {
			for (int row = 0; row < rowSize; row++) {
				for (int col = 0; col < colSize; col++) {
					if (map[row][col][h]) {
						continue;
					}
					count[row][col][h] = getCount(row, col, h);
				}
			}
		}
	}

	static int getCount(int row, int col, int h) {
		int count = 0;

		for (int deltaheight = -1; deltaheight <= 1; deltaheight++) {
			int newHeight = h + deltaheight;
			if (newHeight < 0 || newHeight >= height) {
				continue;
			}

			for (int deltaRow = -1; deltaRow <= 1; deltaRow++) {
				int newRow = row + deltaRow;
				if (newRow < 0 || newRow >= rowSize) {
					continue;
				}

				for (int deltaCol = -1; deltaCol <= 1; deltaCol++) {
					int newCol = col + deltaCol;
					if (newCol < 0 || newCol >= colSize) {
						continue;
					}

					if (deltaheight == 0 && deltaRow == 0 && deltaCol == 0) {
						continue;
					}

					if (map[newRow][newCol][newHeight]) {
						count++;
					}
				}
			}
		}

		return count % 10;
	}

	static void printMap() {
		sb = new StringBuilder();
		for (int h = 0; h < height; h++) {
			for (int row = 0; row < rowSize; row++) {
				for (int col = 0; col < colSize; col++) {
					sb.append(map[row][col][h] ? "*" : count[row][col][h]);
				}
				sb.append("\n");
			}
		}
		System.out.print(sb);
	}
}
