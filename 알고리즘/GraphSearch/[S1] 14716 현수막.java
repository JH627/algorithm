import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14716_현수막 {

	static BufferedReader br;
	static StringTokenizer st;

	static final int[] ADD_ROW = {-1, -1, -1, 0, 0, 1, 1, 1};
	static final int[] ADD_COL = {-1, 0, 1, -1, 1, -1, 0, 1};

	static ArrayList<int[]> startPoints;
	static int rowSize, colSize;
	static boolean[][] map;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(findAreaCount());
	}

	static int findAreaCount() {
		int areaCount = 0;
		for (int[] startPoint : startPoints) {
			int row = startPoint[0];
			int col = startPoint[1];
			if (map[row][col]) {
				areaCount++;
				removeNearArea(row, col);
			}
		}
		return areaCount;
	}

	static void removeNearArea(int row, int col) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[]{row, col});
		map[row][col] = false;

		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			int nowRow = now[0];
			int nowCol = now[1];

			for (int deltaIndex = 0; deltaIndex < 8; deltaIndex++) {
				int newRow = nowRow + ADD_ROW[deltaIndex];
				int newCol = nowCol + ADD_COL[deltaIndex];

				if (newRow < 0 || newRow >= rowSize || newCol < 0 || newCol >= colSize) {
					continue;
				}
				if (!map[newRow][newCol]) {
					continue;
				}
				queue.add(new int[]{newRow, newCol});
				map[newRow][newCol] = false;
			}
		}
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		map = new boolean[rowSize][colSize];

		startPoints = new ArrayList<>();
		for (int row = 0; row < rowSize; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < colSize; col++) {
				if (st.nextToken().charAt(0) == '1') {
					map[row][col] = true;
					startPoints.add(new int[]{row, col});
				}
			}
		}
	}
}
