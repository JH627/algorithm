import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10157_자리배정 {

	static BufferedReader br;
	static StringTokenizer st;

	// 상, 우, 하, 좌
	static final int[] ADD_ROW = {-1, 0, 1, 0};
	static final int[] ADD_COL = {0, 1, 0, -1};
	
	static int rowSize, colSize;
	static int[][] map;
	static int point;

	public static void main(String[] args) throws IOException {
		init();
		getPoint();
	}

	static void getPoint() {
		int count = 0;
		if (point > rowSize * colSize) {
			System.out.print(0);
			return;
		}

		int row = rowSize - 1;
		int col = 0;
		int direction = 0;
		while (++count <= rowSize * colSize) {
			if (count == point) {
				System.out.printf("%d %d", col + 1, rowSize - row);
				return;
			}
			map[row][col] = count;
			int newRow = row + ADD_ROW[direction];
			int newCol = col + ADD_COL[direction];
			if (newRow < 0 || newRow >= rowSize || newCol < 0 || newCol >= colSize || map[newRow][newCol] != 0) {
				direction = (direction + 1) % 4;
				newRow = row + ADD_ROW[direction];
				newCol = col + ADD_COL[direction];
			}
			row = newRow;
			col = newCol;
		}

	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		colSize = Integer.parseInt(st.nextToken());
		rowSize = Integer.parseInt(st.nextToken());
		map = new int[rowSize][colSize];

		point = Integer.parseInt(br.readLine());
	}

}
