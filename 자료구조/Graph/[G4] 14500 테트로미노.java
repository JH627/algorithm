import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static final int[] ADD_ROW = {1, 0, -1, 0};
	static final int[] ADD_COL = {0, 1, 0, -1};

	static int[][] map;
	static boolean[][] visited;
	static int rowSize, colSize, max;
	
	public static void main(String[] args) throws Exception {
		init();
		
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				visited[row][col] = true;
				max = Math.max(max, findMaxValue(1, row, col));
				visited[row][col] = false;
				max = Math.max(max, checkTShape(row, col));
			}
		}
		
		System.out.print(max);
	}
	
	static int findMaxValue(int depth, int row, int col) {	
		int value = map[row][col];
		if (depth == 4) {
			return value;
		}
		
		int max = 0;
		
		for (int k = 0; k < 4; k++) {
			int newRow = row + ADD_ROW[k];
			int newCol = col + ADD_COL[k];
			
			if (newRow < 0 || newRow >= rowSize || newCol < 0 || newCol >= colSize) {
				continue;
			}
			
			if (visited[newRow][newCol]) {
				continue;
			}

			visited[newRow][newCol] = true;
			max = Math.max(max, findMaxValue(depth + 1, newRow, newCol));
			visited[newRow][newCol] = false;
		}

		return value + max;
	}
	
	static int checkTShape(int row, int col) {
		int max = 0;
		
		for (int k = 0; k < 4; k++) {
			boolean isPossible = true;
			int sum = 0;
			for (int idx = 0; idx < 4 && isPossible; idx++) {
				if (k == idx) {
					continue;
				}
				int newRow = row + ADD_ROW[idx];
				int newCol = col + ADD_COL[idx];
				
				if (newRow < 0 || newRow >= rowSize || newCol < 0 || newCol >= colSize) {
					isPossible = false;
					continue;
				}
				
				sum += map[newRow][newCol];
			}
			
			if (isPossible) {
				max = Math.max(max, sum + map[row][col]);
			}
		}
		
		return max;
	}
	
	static void init() throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		
		map = new int[rowSize][colSize];
		visited = new boolean[rowSize][colSize];
		
		for (int row = 0; row < rowSize; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < colSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		max = 0;
	}

}
