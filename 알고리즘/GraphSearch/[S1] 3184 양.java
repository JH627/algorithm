import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3184_양 {

	static BufferedReader br;
	static StringTokenizer st;

	static final int[] ADD_ROW = {-1, 0, 1, 0};
	static final int[] ADD_COL = {0, 1, 0, -1};

	static final char EMPTY = '.';
	static final char WALL = '#';
	static final char SHEEP = 'o';
	static final char WOLF = 'v';

	static int rowSize, colSize;
	static char[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		init();
		findSheepWolfCount();
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());

		map = new char[rowSize][colSize];
		visited = new boolean[rowSize][colSize];
		for (int row = 0; row < rowSize; row++) {
			String line = br.readLine();
			for (int col = 0; col < colSize; col++) {
				map[row][col] = line.charAt(col);
				if (map[row][col] == WALL) {
					visited[row][col] = true;
				}
			}
		}
	}

	static void findSheepWolfCount() {
		int wolfCount = 0;
		int sheepCount = 0;

		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				if (visited[row][col]) {
					continue;
				}
				int result = getAreaResult(row, col);
				if (result > 0) {
					sheepCount += result;
				}
				else {
					wolfCount -= result;
				}
			}
		}

		System.out.printf("%d %d", sheepCount, wolfCount);
	}

	static int getAreaResult(int row, int col) {
		Queue<int[]> toVisit = new LinkedList<>();

		int sheepCount = 0;
		int wolfCount = 0;
		toVisit.add(new int[]{row, col});
		visited[row][col] = true;
		while (!toVisit.isEmpty()) {
			int[] now = toVisit.poll();

			if (map[now[0]][now[1]] == SHEEP) {
				sheepCount++;
			}
			else if (map[now[0]][now[1]] == WOLF) {
				wolfCount++;
			}

			for (int deltaIndex = 0; deltaIndex < 4; deltaIndex++) {
				int newRow = now[0] + ADD_ROW[deltaIndex];
				int newCol = now[1] + ADD_COL[deltaIndex];

				if (newRow < 0 || newRow >= rowSize || newCol < 0 || newCol >= colSize) {
					continue;
				}

				if (visited[newRow][newCol]) {
					continue;
				}

				toVisit.add(new int[]{newRow, newCol});
				visited[newRow][newCol] = true;
			}
		}

		return (sheepCount > wolfCount ? sheepCount : -wolfCount);
	}

}
