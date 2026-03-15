import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_17090_미로탈출하기 {

	static BufferedReader br;
	static StringTokenizer st;

	static final int UNCHECKED = 0;
	static final int CHECKED = 1;
	static final int OUTCELL = 2;

	static int rowSize, colSize;
	static char[][] map;
	static int[][] status;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getCellCount());
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

		status = new int[rowSize][colSize];
	}

	static int getCellCount() {
		int count = 0;

		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				if (status[row][col] == UNCHECKED) {
					count += checkWay(row, col);
				}
			}
		}

		return count;
	}

	static int checkWay(int row, int col) {
		ArrayList<int[]> visited = new ArrayList<>();

		int curRow = row;
		int curCol = col;

		while (true) {
			if (curRow < 0 || curRow >= rowSize || curCol < 0 || curCol >= colSize) {
				for (int[] cell : visited) {
					status[cell[0]][cell[1]] = OUTCELL;
				}
				return visited.size();
			}

			if (status[curRow][curCol] == OUTCELL) {
				for (int[] cell : visited) {
					status[cell[0]][cell[1]] = OUTCELL;
				}
				return visited.size();
			}

			if (status[curRow][curCol] == CHECKED) {
				return 0;
			}

			visited.add(new int[] {curRow, curCol});
			status[curRow][curCol] = CHECKED;

			char dir = map[curRow][curCol];
			switch (dir) {
				case 'U':
					curRow--;
					break;
				case 'D':
					curRow++;
					break;
				case 'L':
					curCol--;
					break;
				case 'R':
					curCol++;
					break;
			}
		}
	}
}
