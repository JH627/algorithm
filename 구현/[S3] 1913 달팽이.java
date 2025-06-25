import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1913_달팽이 {

	static BufferedReader br;
	static StringBuilder sb;

	static final int[] ADD_ROW = {-1, 0, 1, 0};
	static final int[] ADD_COL = {0, 1, 0, -1};

	static int[][] map;
	static int mapSize, findNum;
	static int findRow, findCol;

	public static void main(String[] args) throws IOException {
		init();
		fillMap();
		printMap();
	}

	static void fillMap() {
		int nowRow = mapSize / 2; int nowCol = mapSize / 2;
		map[nowRow][nowCol] = 1;
		findRow = nowRow; findCol = nowCol;

		int time = 2;
		int step = 1;
		while (time <= mapSize * mapSize) {
			for (int directionIndex = 0; directionIndex < 4; directionIndex++) {
				for (int stepIndex = 0; stepIndex < step; stepIndex++) {
					nowRow += ADD_ROW[directionIndex];
					nowCol += ADD_COL[directionIndex];

					if (time == findNum) {
						findRow = nowRow; findCol = nowCol;
					}
					map[nowRow][nowCol] = time++;
					if (time > mapSize * mapSize) {
						break;
					}
				}

				if (directionIndex == 1 || directionIndex == 3) {
					step++;
				}
				if (time > mapSize * mapSize) {
					break;
				}
			}
		}
	}

	static void printMap() {
		for (int row = 0; row < mapSize; row++) {
			for (int col = 0; col < mapSize; col++) {
				sb.append(map[row][col]).append(' ');
			}
			sb.append('\n');
		}
		sb.append(findRow + 1).append(' ').append(findCol + 1);
		System.out.print(sb);
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		mapSize = Integer.parseInt(br.readLine());
		findNum = Integer.parseInt(br.readLine());

		map = new int[mapSize + 1][mapSize + 1];
	}
}
