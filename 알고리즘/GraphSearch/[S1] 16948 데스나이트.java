import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16948_데스나이트 {

	static BufferedReader br;
	static StringTokenizer st;

	static final int[] ADD_ROW = {-2, -2, 0, 0, 2, 2};
	static final int[] ADD_COL = {-1, 1, -2, 2, -1, 1};

	static int mapSize;
	static int startRow, startCol, endRow, endCol;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(findMinDistance());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		mapSize = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		startRow = Integer.parseInt(st.nextToken());
		startCol = Integer.parseInt(st.nextToken());
		endRow = Integer.parseInt(st.nextToken());
		endCol = Integer.parseInt(st.nextToken());
	}

	static int findMinDistance() {
		Queue<int[]> toVisit = new LinkedList<>();
		boolean[][] visited = new boolean[mapSize][mapSize];

		toVisit.add(new int[]{startRow, startCol, 0});
		while (!toVisit.isEmpty()) {
			int[] now = toVisit.poll();

			for (int deltaIndex = 0; deltaIndex < 6; deltaIndex++) {
				int newRow = now[0] + ADD_ROW[deltaIndex];
				int newCol = now[1] + ADD_COL[deltaIndex];

				if (newRow < 0 || newRow >= mapSize || newCol < 0 || newCol >= mapSize) {
					continue;
				}

				if (visited[newRow][newCol]) {
					continue;
				}

				if (newRow == endRow && newCol == endCol) {
					return now[2] + 1;
				}

				visited[newRow][newCol] = true;
				toVisit.add(new int[]{newRow, newCol, now[2] + 1});
			}

		}

		return -1;
	}

}
