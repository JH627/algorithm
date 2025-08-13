import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_31575_도시와비트코인 {

	static BufferedReader br;
	static StringTokenizer st;

	static final int[] ADD_ROW = {0, 1};
	static final int[] ADD_COL = {1, 0};

	static int rowSize, colSize;
	static boolean[][] map;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(hasPath() ? "Yes" : "No");
	}

	static boolean hasPath() {
		boolean[][] visited = new boolean[rowSize][colSize];

		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[]{0, 0});
		visited[0][0] = true;
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			int nowRow = now[0];
			int nowCol = now[1];

			if (nowRow == rowSize - 1 && nowCol == colSize - 1) {
				return true;
			}

			for (int deltaIndex = 0; deltaIndex < 2; deltaIndex++) {
				int newRow = nowRow + ADD_ROW[deltaIndex];
				int newCol = nowCol + ADD_COL[deltaIndex];
				if (newRow < 0 || newRow >= rowSize || newCol < 0 || newCol >= colSize) {
					continue;
				}
				if (!map[newRow][newCol]) {
					continue;
				}
				if (visited[newRow][newCol]) {
					continue;
				}
				visited[newRow][newCol] = true;
				queue.offer(new int[]{newRow, newCol});
			}
		}

		return false;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		colSize = Integer.parseInt(st.nextToken());
		rowSize = Integer.parseInt(st.nextToken());
		map = new boolean[rowSize][colSize];
		for (int row = 0; row < rowSize; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < colSize; col++) {
				if (st.nextToken().charAt(0) == '1') {
					map[row][col] = true;
				}
			}
		}
	}
}
