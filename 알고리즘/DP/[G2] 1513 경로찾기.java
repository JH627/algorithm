import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1513_경로찾기 {

	static BufferedReader br;
	static StringTokenizer st;

	static final int MOD = 1000007;

	static int rowSize, colSize, arcadeCount;
	static int[][] map;
	static int[] result;

	public static void main(String[] args) throws IOException {
		init();
		findMaxArcadeCount();
		printResult();
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		arcadeCount = Integer.parseInt(st.nextToken());

		map = new int[rowSize][colSize];

		for (int arcade = 1; arcade <= arcadeCount; arcade++) {
			st = new StringTokenizer(br.readLine());

			int row = Integer.parseInt(st.nextToken()) - 1;
			int col = Integer.parseInt(st.nextToken()) - 1;

			map[row][col] = arcade;
		}

		result = new int[arcadeCount + 1];
	}

	static void findMaxArcadeCount() {
		int[][][][] dp = new int[rowSize][colSize][arcadeCount + 1][arcadeCount + 1];

		if (map[0][0] == 0) {
			dp[0][0][0][0] = 1;
		}
		else {
			dp[0][0][map[0][0]][1] = 1;
		}

		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				if (row == 0 && col == 0) {
					continue;
				}

				if (map[row][col] == 0) {
					for (int lastArcade = 0; lastArcade <= arcadeCount; lastArcade++) {
						for (int count = 0; count <= arcadeCount; count++) {

							if (row > 0) {
								dp[row][col][lastArcade][count] += dp[row - 1][col][lastArcade][count];
							}

							if (col > 0) {
								dp[row][col][lastArcade][count] += dp[row][col - 1][lastArcade][count];
							}

							dp[row][col][lastArcade][count] %= MOD;
						}
					}

					continue;
				}

				int currentArcade = map[row][col];

				for (int lastArcade = 0; lastArcade < currentArcade; lastArcade++) {
					for (int count = 0; count < arcadeCount; count++) {

						if (row > 0) {
							dp[row][col][currentArcade][count + 1] += dp[row - 1][col][lastArcade][count];
						}

						if (col > 0) {
							dp[row][col][currentArcade][count + 1] += dp[row][col - 1][lastArcade][count];
						}

						dp[row][col][currentArcade][count + 1] %= MOD;
					}
				}
			}
		}

		for (int count = 0; count <= arcadeCount; count++) {
			for (int lastArcade = 0; lastArcade <= arcadeCount; lastArcade++) {
				result[count] += dp[rowSize - 1][colSize - 1][lastArcade][count];
				result[count] %= MOD;
			}
		}
	}

	static void printResult() {
		StringBuilder sb = new StringBuilder();

		for (int count : result) {
			sb.append(count).append(" ");
		}

		System.out.print(sb);
	}
}
