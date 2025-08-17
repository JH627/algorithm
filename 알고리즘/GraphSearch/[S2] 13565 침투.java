import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_13565_침투 {

	static BufferedReader br;
	static StringTokenizer st;

	static final int[] ADD_ROW = {-1, 0, 1, 0};
	static final int[] ADD_COL = {0, -1, 0, 1};

	static int rowSize, colSize;
	static boolean[][] map, visited;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(findPath() ? "YES" : "NO");
	}

	static boolean findPath() {
		for (int col = 0; col < colSize; col++) {
			if (!visited[0][col] && map[0][col]) {
				if (checkPath(col)) {
					return true;
				}
			}
		}
		return false;
	}

	static boolean checkPath(int col) {
		Queue<int[]> toVisit = new LinkedList<>();
		toVisit.add(new int[]{0, col});
		visited[0][col] = true;
		while (!toVisit.isEmpty()) {
			int[] now = toVisit.poll();
			if (now[0] == rowSize - 1) {
				return true;
			}

			for (int deltaIndex = 0; deltaIndex < 4; deltaIndex++) {
				int newRow = now[0] + ADD_ROW[deltaIndex];
				int newCol = now[1] + ADD_COL[deltaIndex];

				if (newRow < 0 || newRow >= rowSize || newCol < 0 || newCol >= colSize) {
					continue;
				}

				if (map[newRow][newCol] && !visited[newRow][newCol]) {
					toVisit.add(new int[]{newRow, newCol});
					visited[newRow][newCol] = true;
				}
			}
		}

		return false;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		map = new boolean[rowSize][colSize];
		visited = new boolean[rowSize][colSize];

		for (int row = 0; row < rowSize; row++) {
			String line = br.readLine();
			for (int col = 0; col < colSize; col++) {
				if (line.charAt(col) == '0') {
					map[row][col] = true;
				}
			}
		}
	}
}
