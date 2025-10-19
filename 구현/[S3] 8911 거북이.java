import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_8911_거북이 {

	static BufferedReader br;
	static StringBuilder sb;

	static final int[] ADD_ROW = {-1, 0, 1, 0};
	static final int[] ADD_COL = {0, 1, 0, -1};

	static char[] command;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 0; testCase < TC; testCase++) {
			command = br.readLine().toCharArray();
			int size = findMinSize();
			sb.append(size).append("\n");
		}
		System.out.print(sb);
	}

	static int findMinSize() {
		int minRow = 0, minCol = 0;
		int maxRow = 0, maxCol = 0;

		int curDirection = 0;
		int curRow = 0, curCol = 0;
		for (char operation : command) {
			switch (operation) {
				case 'F':
					curRow += ADD_ROW[curDirection];
					curCol += ADD_COL[curDirection];
					minRow = Math.min(minRow, curRow);
					minCol = Math.min(minCol, curCol);
					maxRow = Math.max(maxRow, curRow);
					maxCol = Math.max(maxCol, curCol);
					break;
				case 'B':
					int backDirection = (curDirection + 2) % 4;
					curRow += ADD_ROW[backDirection];
					curCol += ADD_COL[backDirection];
					minRow = Math.min(minRow, curRow);
					minCol = Math.min(minCol, curCol);
					maxRow = Math.max(maxRow, curRow);
					maxCol = Math.max(maxCol, curCol);
					break;
				case 'L':
					curDirection = (curDirection - 1 + 4) % 4;
					break;
				case 'R':
					curDirection = (curDirection + 1) % 4;
					break;
			}
		}

		return Math.abs(maxCol - minCol) * Math.abs(maxRow - minRow);
	}

}
