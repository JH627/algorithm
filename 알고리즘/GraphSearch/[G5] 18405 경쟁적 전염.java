import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_18405_경쟁적전염 {

	static BufferedReader br;
	static StringTokenizer st;

	static final int[] ADD_ROW = {-1, 0, 1, 0};
	static final int[] ADD_COL = {0, 1, 0, -1};

	static class Point {
		int row, col, time, type;

		public Point(int row, int col, int time, int type) {
			this.row = row;
			this.col = col;
			this.time = time;
			this.type = type;
		}
	}

	static int mapSize, virusCount;
	static int[][] map;
	static List<Point> viruses;

	static int timeLimit, targetRow, targetCol;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getVirusStatus());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		mapSize = Integer.parseInt(st.nextToken());
		virusCount = Integer.parseInt(st.nextToken());

		map = new int[mapSize][mapSize];

		viruses = new ArrayList<>();
		for (int row = 0; row < mapSize; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < mapSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());

				if (map[row][col] != 0) {
					viruses.add(new Point(row, col, 0, map[row][col]));
				}
			}
		}

		st = new StringTokenizer(br.readLine());
		timeLimit = Integer.parseInt(st.nextToken());
		targetRow = Integer.parseInt(st.nextToken()) - 1;
		targetCol = Integer.parseInt(st.nextToken()) - 1;
	}

	static int getVirusStatus() {
		viruses.sort(Comparator.comparingInt(p -> p.type));
		Queue<Point> queue = new LinkedList<>(viruses);

		while (!queue.isEmpty()) {
			Point now = queue.poll();

			if (now.time == timeLimit) {
				break;
			}

			for (int deltaIndex = 0; deltaIndex < 4; deltaIndex++) {
				int newRow = now.row + ADD_ROW[deltaIndex];
				int newCol = now.col + ADD_COL[deltaIndex];

				if (newRow < 0 || newRow >= mapSize || newCol < 0 || newCol >= mapSize) {
					continue;
				}

				if (map[newRow][newCol] != 0) {
					continue;
				}

				map[newRow][newCol] = now.type;
				queue.add(new Point(newRow, newCol, now.time + 1, now.type));
			}
		}

		return map[targetRow][targetCol];
	}
}
