import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int N;
	static final int[] ADD_ROW = {1, 1, 1, 0, 0, -1, -1, -1};
	static final int[] ADD_COL = {-1, 0, 1, -1, 1, -1, 0, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		int[][] count = new int[N][N];
		for (int row = 0; row < N; row++) {
			String s = br.readLine();
			for (int col = 0; col < N; col++) {
				if (s.charAt(col) != '.') {
					count[row][col] = 1000;
					for (int k = 0; k < 8; k++) {
						int newRow = row + ADD_ROW[k];
						int newCol = col + ADD_COL[k];

						if (newRow < 0 || newRow >= N || newCol < 0 || newCol >= N) {
							continue;
						}

						count[newRow][newCol] += s.charAt(col) - '0';
					}
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				if (count[row][col] < 10) {
					sb.append(count[row][col]);
				}
				else if (count[row][col] < 1000) {
					sb.append('M');
				}
				else {
					sb.append('*');
				}
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}
}
