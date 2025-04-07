import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1051_숫자정사각형 {

	static BufferedReader br;
	static StringTokenizer st;

	static int rowSize, colSize;
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());

		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());

		arr = new int[rowSize][colSize];
		for (int row = 0; row < rowSize; row++) {
			String s = br.readLine();
			for (int col = 0; col < colSize; col++) {
				arr[row][col] = s.charAt(col) - '0';
			}
		}

		int len = Math.min(rowSize, colSize);
		while (len > 1) {
			for (int row = 0; row <= rowSize - len; row++) {
				for (int col = 0; col <= colSize - len; col++) {
					if (check(row, col, len - 1)) {
						System.out.print(len * len);
						return;
					}
				}
			}
			len--;
		}

		System.out.print(1);
	}

	static boolean check(int row, int col, int len) {
		int num = arr[row][col];
		return num == arr[row][col + len]
			&& num == arr[row + len][col]
			&& num == arr[row + len][col + len];
	}
}
