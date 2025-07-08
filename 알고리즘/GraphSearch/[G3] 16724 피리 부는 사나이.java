import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16724_피리부는사나이 {

	static BufferedReader br;
	static StringTokenizer st;
	
	// U, D, L, R
	static final int[] ADD_ROW = {-1, 1, 0, 0};
	static final int[] ADD_COL = {0, 0, -1, 1};
	
	static int rowSize, colSize;
	static char[][] map;
	static int[][] groupNumber;
	static boolean[] filledGroup;
	
	public static void main(String[] args) throws Exception {
		init();
		System.out.print(getGroupCount());
	}
	
	static int getGroupCount() {
		int count = 0;
		int sequence = 1;
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				sequence++;
				if (groupNumber[row][col] == 0) {
					int ownGroupNumber = findOwnGroupNumber(row, col, sequence);
					if (!filledGroup[ownGroupNumber]) {
						filledGroup[ownGroupNumber] = true;
						count++;
					}
				}
			}
		}
		
		return count;
	}
	
	static int findOwnGroupNumber(int row, int col, int startGroupNumber) {
		char direction = map[row][col];
		
		groupNumber[row][col] = startGroupNumber;
		int newRow = 0, newCol = 0;
		switch (direction) {
			case 'U':
				newRow = row + ADD_ROW[0];
				newCol = col + ADD_COL[0];
				break;
			case 'D':
				newRow = row + ADD_ROW[1];
				newCol = col + ADD_COL[1];
				break;
			case 'L':
				newRow = row + ADD_ROW[2];
				newCol = col + ADD_COL[2];
				break;
			case 'R':
				newRow = row + ADD_ROW[3];
				newCol = col + ADD_COL[3];
				break;
		}
		
		if (groupNumber[newRow][newCol] == 0) {
			groupNumber[row][col] = findOwnGroupNumber(newRow, newCol, startGroupNumber);
		}
		else {
			groupNumber[row][col] = groupNumber[newRow][newCol];
		}
		
		return groupNumber[row][col];
	}
	
	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		map = new char[rowSize][colSize];
		groupNumber = new int[rowSize][colSize];
		filledGroup = new boolean[1000002];
		
		for (int row = 0; row < rowSize; row++) {
			char[] line = br.readLine().toCharArray();
			for (int col = 0; col < colSize; col++) {
				map[row][col] = line[col];
			}
		}
		
	}
	
}
