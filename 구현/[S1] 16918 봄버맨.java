import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16918_봄버맨 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static final int BOOM_TIME = 3;

	static final int[] ADD_ROW = {-1, 0, 1, 0};
	static final int[] ADD_COL = {0, -1, 0, 1};

	static int rowSize, colSize, timeLimit;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		init();
		simulate();
		printMap();
	}

	static void simulate() {
		for (int time = 2; time <= timeLimit; time++) {
			if (time % 2 == 0) {
				fillEmptyMap(time);
			}
			else {
				boom(time);
			}
		}
	}

	static void fillEmptyMap(int time) {
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				if (map[row][col] == 0) {
					map[row][col] = time + BOOM_TIME;
				}
			}
		}
	}

	static void boom(int time) {
		boolean[][] destroyed = new boolean[rowSize][colSize];

		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				if (map[row][col] == time) {
					destroyed[row][col] = true;

					for (int deltaIndex = 0; deltaIndex < 4; deltaIndex++) {
						int newRow = row + ADD_ROW[deltaIndex];
						int newCol = col + ADD_COL[deltaIndex];
						if (newRow < 0 || newRow >= rowSize || newCol < 0 || newCol >= colSize) {
							continue;
						}
						destroyed[newRow][newCol] = true;
					}
				}
			}
		}

		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				if (destroyed[row][col]) {
					map[row][col] = 0;
				}
			}
		}
	}

	static void printMap() {
		sb = new StringBuilder();
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				sb.append(map[row][col] == 0 ? '.' : 'O');
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
		timeLimit = Integer.parseInt(st.nextToken());

		map = new int[rowSize][colSize];
		for (int row = 0; row < rowSize; row++) {
			String line = br.readLine();
			for (int col = 0; col < colSize; col++) {
				if (line.charAt(col) == 'O') {
					map[row][col] = BOOM_TIME;
				}
			}
		}
	}
}
