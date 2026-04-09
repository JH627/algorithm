import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_18428_감시피하기 {

	static BufferedReader br;
	static StringTokenizer st;

	static final int[] ADD_ROW = {-1, 0, 1, 0};
	static final int[] ADD_COL = {0, -1, 0, 1};

	static final char TEACHER = 'T';
	static final char STUDENT = 'S';
	static final char EMPTY = 'X';
	static final char WALL = 'W';
	static final char CANDIDATE = 'C';

	static int mapSize;
	static char[][] map;
	static List<int[]> candidates;

	public static void main(String[] args) throws IOException {
		init();
		if (!findCandidateZone()) {
			System.out.print("NO");
			return;
		}
		System.out.print(checkPossible(0, 0) ? "YES" : "NO");
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		mapSize = Integer.parseInt(br.readLine());
		map = new char[mapSize][mapSize];
		for (int row = 0; row < mapSize; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < mapSize; col++) {
				map[row][col] = st.nextToken().charAt(0);
			}
		}

		candidates = new ArrayList<>();
	}

	static boolean findCandidateZone() {
		for (int row = 0; row < mapSize; row++) {
			for (int col = 0; col < mapSize; col++) {
				if (map[row][col] != TEACHER) {
					continue;
				}

				for (int deltaIndex = 0; deltaIndex < 4; deltaIndex++) {
					for (int mul = 1; ; mul++) {
						int newRow = row + ADD_ROW[deltaIndex] * mul;
						int newCol = col + ADD_COL[deltaIndex] * mul;

						if (newRow < 0 || newRow >= mapSize || newCol < 0 || newCol >= mapSize) {
							break;
						}

						if (map[newRow][newCol] == WALL) {
							break;
						}

						if (map[newRow][newCol] == STUDENT && mul == 1) {
							return false;
						}

						if (map[newRow][newCol] == EMPTY) {
							map[newRow][newCol] = CANDIDATE;
							candidates.add(new int[]{newRow, newCol});
						}
					}
				}
			}
		}

		return true;
	}

	static boolean checkPossible(int depth, int start) {
		if (depth == 3) {
			return checkMap();
		}

		for (int index = start; index < candidates.size(); index++) {
			int[] position = candidates.get(index);
			int row = position[0];
			int col = position[1];

			if (map[row][col] != CANDIDATE) {
				continue;
			}

			map[row][col] = WALL;
			if (checkPossible(depth + 1, index + 1)) {
				return true;
			}
			map[row][col] = CANDIDATE;
		}

		return false;
	}

	static boolean checkMap() {
		for (int row = 0; row < mapSize; row++) {
			for (int col = 0; col < mapSize; col++) {
				if (map[row][col] != TEACHER) {
					continue;
				}

				for (int deltaIndex = 0; deltaIndex < 4; deltaIndex++) {
					for (int mul = 1; ; mul++) {
						int newRow = row + ADD_ROW[deltaIndex] * mul;
						int newCol = col + ADD_COL[deltaIndex] * mul;

						if (newRow < 0 || newRow >= mapSize || newCol < 0 || newCol >= mapSize) {
							break;
						}
						if (map[newRow][newCol] == WALL) {
							break;
						}

						if (map[newRow][newCol] == STUDENT) {
							return false;
						}
					}
				}
			}
		}

		return true;
	}
}
