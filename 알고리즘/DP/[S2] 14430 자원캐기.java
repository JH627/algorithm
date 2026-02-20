import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14430_자원캐기 {

	static BufferedReader br;
	static StringTokenizer st;

	static int rowSize, colSize;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getMaxPoint());
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

	static int getMaxPoint() {
		for (int row = 1; row < rowSize; row++) {
			map[row][0] += map[row - 1][0];
		}

		for (int col = 1; col < colSize; col++) {
			map[0][col] += map[0][col - 1];
		}

		for (int row = 1; row < rowSize; row++) {
			for (int col = 1; col < colSize; col++) {
				map[row][col] += Math.max(map[row - 1][col], map[row][col - 1]);
			}
		}

		return map[rowSize - 1][colSize - 1];
	}
}
