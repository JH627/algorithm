import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2638_치즈 {

	static BufferedReader br;
	static StringTokenizer st;

	static final int[] ADD_ROW = {-1, 0, 1, 0};
	static final int[] ADD_COL = {0, -1, 0, 1};

	static int rowSize, colSize;
	static int cheeseCount;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getTime());
	}

	static int getTime() {
		int time = 3;
		while (cheeseCount != 0) {
			time++;
			cheeseCount -= removeCheese(time);
		}
		return time - 3;
	}

	static int removeCheese(int time) {
		boolean[][] visited = new boolean[rowSize][colSize];
		Queue<int[]> toVisit = new LinkedList<>();
		toVisit.add(new int[]{0, 0});
		visited[0][0] = true;
		int count = 0;
		while (!toVisit.isEmpty()) {
			int[] now = toVisit.poll();
			int nowRow = now[0];
			int nowCol = now[1];

			for (int index = 0; index < 4; index++) {
				int newRow = nowRow + ADD_ROW[index];
				int newCol = nowCol + ADD_COL[index];

				if (newRow < 0 || newRow >= rowSize || newCol < 0 || newCol >= colSize) {
					continue;
				}

				// 만약 현재 지워야하는 치즈가 두 방면에서 방문한 경우
				if (map[newRow][newCol] == 1 && visited[newRow][newCol]) {
					map[newRow][newCol] = time;
					count++;
				}

				if (visited[newRow][newCol]) {
					continue;
				}
				visited[newRow][newCol] = true;

				if (map[newRow][newCol] == 0) {
					toVisit.add(new int[]{newRow, newCol});
				}

				if (map[newRow][newCol] >= 2 && map[newRow][newCol] < time) {
					toVisit.add(new int[]{newRow, newCol});
				}
			}
		}

		return count;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());

		cheeseCount = 0;
		map = new int[rowSize][colSize];
		for (int row = 0; row < rowSize; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < colSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
				if (map[row][col] == 1) {
					cheeseCount++;
				}
			}
		}
	}

}
