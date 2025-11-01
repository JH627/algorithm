import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_4396_지뢰찾기 {

	static BufferedReader br;
	static StringBuilder sb;

	static final int[] ADD_ROW = {-1, -1, -1, 0, 0, 1, 1, 1};
	static final int[] ADD_COL = {-1, 0, 1, -1, 1, -1, 0, 1};

	static int size;
	static char[][] map, output;
	static boolean gameOut;

	public static void main(String[] args) throws IOException {
		init();
		fillMap();
		printMap();
	}

	static void fillMap() throws IOException {
		output = new char[size][size];
		gameOut = false;
		for (int row = 0; row < size; row++) {
			String line = br.readLine();
			for (int col = 0; col < size; col++) {
				if (line.charAt(col) == '.') {
					output[row][col] = '.';
					continue;
				}
				if (map[row][col] == '*') {
					gameOut = true;
					continue;
				}

				int count = 0;
				for (int index = 0; index < 8; index++) {
					int newRow = row + ADD_ROW[index];
					int newCol = col + ADD_COL[index];

					if (newRow < 0 || newRow >= size || newCol < 0 || newCol >= size) {
						continue;
					}
					if (map[newRow][newCol] == '*') {
						count++;
					}
				}
				output[row][col] = (char)('0' + count);
			}
		}
	}

	static void printMap() {
		sb = new StringBuilder();
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				if (gameOut && map[row][col] == '*') {
					sb.append("*");
					continue;
				}
				sb.append(output[row][col]);
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		size = Integer.parseInt(br.readLine());
		map = new char[size][size];
		for (int row = 0; row < size; row++) {
			String line = br.readLine();
			for (int col = 0; col < size; col++) {
				map[row][col] = line.charAt(col);
			}
		}
	}
}
