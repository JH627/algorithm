import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16439_치킨치킨치킨 {

	static BufferedReader br;
	static StringTokenizer st;

	static int rowCount, colCount;
	static int[][] valueMap;
	static boolean[] selectedColumns;
	static int maxSum;

	public static void main(String[] args) throws IOException {
		init();
		selectColumns(0, 0);
		System.out.print(maxSum);
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		rowCount = Integer.parseInt(st.nextToken());
		colCount = Integer.parseInt(st.nextToken());

		valueMap = new int[rowCount][colCount];
		selectedColumns = new boolean[colCount];

		for (int row = 0; row < rowCount; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < colCount; col++) {
				valueMap[row][col] = Integer.parseInt(st.nextToken());
			}
		}
	}

	static void selectColumns(int startCol, int selectedCount) {
		if (selectedCount == 3) {
			int currentSum = 0;

			for (int row = 0; row < rowCount; row++) {
				int rowMax = 0;
				for (int col = 0; col < colCount; col++) {
					if (selectedColumns[col]) {
						rowMax = Math.max(rowMax, valueMap[row][col]);
					}
				}
				currentSum += rowMax;
			}

			maxSum = Math.max(maxSum, currentSum);
			return;
		}

		for (int col = startCol; col < colCount; col++) {
			if (!selectedColumns[col]) {
				selectedColumns[col] = true;
				selectColumns(col + 1, selectedCount + 1);
				selectedColumns[col] = false;
			}
		}
	}
}
