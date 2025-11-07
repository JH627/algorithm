import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_29700_우당탕탕영화예매 {

	static BufferedReader br;
	static StringTokenizer st;

	static int rowSize, colSize, userLength;
	static boolean[][] map;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(findCount());
	}

	static int findCount() {
		int count = 0;
		for (int row = 0; row < rowSize; row++) {
			int len = 0;
			for (int col = 0; col < colSize; col++) {
				if (map[row][col]) {
					len = 0;
				}
				else {
					if (++len >= userLength) {
						count++;
					}
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
		userLength = Integer.parseInt(st.nextToken());

		map = new boolean[rowSize][colSize];
		for (int row = 0; row < rowSize; row++) {
			String line = br.readLine();
			for (int col = 0; col < colSize; col++) {
				if (line.charAt(col) == '1') {
					map[row][col] = true;
				}
			}
		}
	}

}
