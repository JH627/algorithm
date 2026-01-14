import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_16955_오목이길수있을까 {

	static final int SIZE = 10;
	static char[][] map;

	static final int[] ADD_ROW = {0, 1, 1, 1};
	static final int[] ADD_COL = {1, 0, 1, -1};

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(canWin() ? "1" : "0");
	}

	static boolean canWin() {
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				if (map[row][col] == '.') {
					map[row][col] = 'X';
					if (isFiveOrMore(row, col)) {
						return true;
					}
					map[row][col] = '.';
				}
			}
		}
		return false;
	}

	static boolean isFiveOrMore(int row, int col) {
		for (int deltaIndex = 0; deltaIndex < 4; deltaIndex++) {
			int count = 1;
			count += count(row, col, ADD_ROW[deltaIndex], ADD_COL[deltaIndex]);
			count += count(row, col, -ADD_ROW[deltaIndex], -ADD_COL[deltaIndex]);

			if (count >= 5) {
				return true;
			}
		}
		return false;
	}

	static int count(int row, int col, int addRow, int addCol) {
		int newRow = row + addRow;
		int newCol = col + addCol;
		int count = 0;
		while (newRow >= 0 && newRow < SIZE && newCol >= 0 && newCol < SIZE && map[newRow][newCol] == 'X') {
			count++;
			newRow += addRow;
			newCol += addCol;
		}
		return count;
	}

	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new char[SIZE][SIZE];
		for (int row = 0; row < SIZE; row++) {
			String line = br.readLine();
			for (int col = 0; col < SIZE; col++) {
				map[row][col] = line.charAt(col);
			}
		}
	}
}
