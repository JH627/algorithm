import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2169_로봇조종하기 {

	static BufferedReader br;
	static StringTokenizer st;

	static int rowSize, colSize;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(findMaxCost());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());

		map = new int[rowSize][colSize];
		for (int row = 0; row < rowSize; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < colSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
	}

	static int findMaxCost() {
		int[][] maxCost = new int[rowSize][colSize];
		maxCost[0][0] = map[0][0];

		for (int col = 1; col < colSize; col++) {
			maxCost[0][col] = maxCost[0][col - 1] + map[0][col];
		}

		for (int row = 1; row < rowSize; row++) {
			int[] left = new int[colSize];
			int[] right = new int[colSize];

			left[0] = maxCost[row - 1][0] + map[row][0];
			for (int col = 1; col < colSize; col++) {
				left[col] = Math.max(left[col - 1], maxCost[row - 1][col]) + map[row][col];
			}

			right[colSize - 1] = maxCost[row - 1][colSize - 1] + map[row][colSize - 1];
			for (int col = colSize - 2; col >= 0; col--) {
				right[col] = Math.max(right[col + 1], maxCost[row - 1][col]) + map[row][col];
			}

			for (int col = 0; col < colSize; col++) {
				maxCost[row][col] = Math.max(left[col], right[col]);
			}
		}

		return maxCost[rowSize - 1][colSize - 1];
	}
}
