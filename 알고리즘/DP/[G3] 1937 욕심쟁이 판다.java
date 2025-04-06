import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1937_욕심쟁이판다 {

	static BufferedReader br;
	static StringTokenizer st;

	static final int[] ADD_ROW = {-1, 0, 1, 0};
	static final int[] ADD_COL = {0, -1, 0, 1};

	static int mapSize;
	static int[][] map, dist;

	public static void main(String[] args) throws IOException {
		init();

		int max = 0;
		for (int row = 0; row < mapSize; row++) {
			for (int col = 0; col < mapSize; col++) {
				max = Math.max(max, findMaxDistance(row, col));
			}
		}

		System.out.print(max);
	}

	static int findMaxDistance(int row, int col) {
		if (dist[row][col] != 0) {
			return dist[row][col];
		}

		dist[row][col] = 1;

		for (int index = 0; index < 4; index++) {
			int newRow = row + ADD_ROW[index];
			int newCol = col + ADD_COL[index];

			if (newRow < 0 || newRow >= mapSize || newCol < 0 || newCol >= mapSize) {
				continue;
			}

			if (map[newRow][newCol] > map[row][col]) {
				dist[row][col] = Math.max(dist[row][col], findMaxDistance(newRow, newCol) + 1);
			}
		}

		return dist[row][col];
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		mapSize = Integer.parseInt(br.readLine());

		map = new int[mapSize][mapSize];
		dist = new int[mapSize][mapSize];
		for (int row = 0; row < mapSize; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < mapSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
