import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17144_미세먼지_안녕 {

	static BufferedReader br;
	static StringTokenizer st;

	static final int[] ADD_ROW = {-1, 0, 1, 0};
	static final int[] ADD_COL = {0, -1, 0, 1};

	static int rowSize, colSize, timeLimit;
	static int[][] map;
	static int upperMachineRow, lowerMachineRow;
	static int dustSum;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getDustAmount());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());

		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		timeLimit = Integer.parseInt(st.nextToken());

		upperMachineRow = -1;
		lowerMachineRow = -1;

		dustSum = 0;
		map = new int[rowSize][colSize];
		for (int row = 0; row < rowSize; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < colSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
				if (map[row][col] == -1) {
					if (upperMachineRow == -1) {
						upperMachineRow = row;
					} else {
						lowerMachineRow = row;
					}
				} else {
					dustSum += map[row][col];
				}
			}
		}
	}

	static int getDustAmount() {
		int currentDust = dustSum;

		for (int time = 1; time <= timeLimit; time++) {
			diffusion();
			currentDust += clean();

			if (currentDust == 0) {
				break;
			}
		}

		return currentDust;
	}

	static void diffusion() {
		int[][] nextStatus = new int[rowSize][colSize];
		nextStatus[upperMachineRow][0] = -1;
		nextStatus[lowerMachineRow][0] = -1;

		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				if (map[row][col] <= 0) {
					continue;
				}

				int spreadAmount = map[row][col] / 5;
				int ableAreaCount = 0;

				for (int deltaIndex = 0; deltaIndex < 4; deltaIndex++) {
					int newRow = row + ADD_ROW[deltaIndex];
					int newCol = col + ADD_COL[deltaIndex];

					if (newRow < 0 || newRow >= rowSize || newCol < 0 || newCol >= colSize) {
						continue;
					}

					if (map[newRow][newCol] == -1) {
						continue;
					}

					nextStatus[newRow][newCol] += spreadAmount;
					ableAreaCount++;
				}

				nextStatus[row][col] += map[row][col] - spreadAmount * ableAreaCount;
			}
		}

		map = nextStatus;
	}

	static int clean() {
		int removedDust = map[upperMachineRow - 1][0] + map[lowerMachineRow + 1][0];

		// 위쪽
		for (int row = upperMachineRow - 1; row > 0; row--) {
			map[row][0] = map[row - 1][0];
		}
		for (int col = 0; col < colSize - 1; col++) {
			map[0][col] = map[0][col + 1];
		}
		for (int row = 0; row < upperMachineRow; row++) {
			map[row][colSize - 1] = map[row + 1][colSize - 1];
		}
		for (int col = colSize - 1; col > 1; col--) {
			map[upperMachineRow][col] = map[upperMachineRow][col - 1];
		}
		map[upperMachineRow][1] = 0;

		// 아래쪽
		for (int row = lowerMachineRow + 1; row < rowSize - 1; row++) {
			map[row][0] = map[row + 1][0];
		}
		for (int col = 0; col < colSize - 1; col++) {
			map[rowSize - 1][col] = map[rowSize - 1][col + 1];
		}
		for (int row = rowSize - 1; row > lowerMachineRow; row--) {
			map[row][colSize - 1] = map[row - 1][colSize - 1];
		}
		for (int col = colSize - 1; col > 1; col--) {
			map[lowerMachineRow][col] = map[lowerMachineRow][col - 1];
		}
		map[lowerMachineRow][1] = 0;

		map[upperMachineRow][0] = -1;
		map[lowerMachineRow][0] = -1;

		return -removedDust;
	}
}
