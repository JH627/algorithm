import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_4108_지뢰찾기 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static final int[] ADD_ROW = {-1, -1, -1, 0, 0, 1, 1, 1};
	static final int[] ADD_COL = {-1, 0, 1, -1, 1, -1, 0, 1};
	static final int INF = 100;
	
	static int rowSize, colSize;
	static int[][] count;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		while (true) {
			if (!init()) {
				break;	
			}
			
			fillMap();
			
			printMap();
		}
		
		System.out.print(sb);
	}
	
	static void fillMap() throws Exception {
		for (int row = 0; row < rowSize; row++) {
			String line = br.readLine();
			for (int col = 0; col < colSize; col++) {
				if (line.charAt(col) == '*') {
					count[row][col] = INF;
					int newRow, newCol;
					for (int deltaIndex = 0; deltaIndex < 8; deltaIndex++) {
						newRow = row + ADD_ROW[deltaIndex];
						newCol = col + ADD_COL[deltaIndex];
						
						if (newRow < 0 || newRow >= rowSize || newCol < 0 || newCol >= colSize) {
							continue;
						}
						
						count[newRow][newCol]++;
					}
				}
			}
		}
		
		
		
	}
	
	static void printMap() {
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				sb.append((count[row][col] >= INF) ? "*" : count[row][col]);
			}
			sb.append("\n");
		}
	}
	
	static boolean init() throws Exception {
		st = new StringTokenizer(br.readLine());
		
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		
		if (rowSize == 0 && colSize == 0) {
			return false;
		}
		
		count = new int[rowSize][colSize];
		return true;
	}

}
