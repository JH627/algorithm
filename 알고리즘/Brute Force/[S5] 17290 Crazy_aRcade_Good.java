import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17290_CrazyaRcadeGood {

	static BufferedReader br;
	static StringTokenizer st;

	static int row, col;
	static int startRow, startCol;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(findMinDistance());
	}

	static int findMinDistance() {
		int min = Integer.MAX_VALUE;
		for (int rowIndex = 0; rowIndex < 10; rowIndex++) {
			for (int colIndex = 0; colIndex < 10; colIndex++) {
				if (((row & (1 << rowIndex)) == 0) && ((col & (1 << colIndex)) == 0)) {
					min = Math.min(min, (Math.abs(rowIndex - startRow) + Math.abs(colIndex - startCol)));
				}
			}
		}

		return min;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		startRow = Integer.parseInt(st.nextToken()) - 1;
		startCol = Integer.parseInt(st.nextToken()) - 1;

		row = 0;
		col = 0;

		for (int rowIndex = 0; rowIndex < 10; rowIndex++) {
			String line = br.readLine();
			for (int colIndex = 0; colIndex < 10; colIndex++) {
				if (line.charAt(colIndex) == 'o') {
					row |= 1 << rowIndex;
					col |= 1 << colIndex;
				}
			}
		}
	}

}
