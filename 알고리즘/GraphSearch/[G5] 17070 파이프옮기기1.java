import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17070_파이프옮기기1 {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static final int[] ADD_ROW = {0, 1, 1};
	static final int[] ADD_COL = {1, 0, 1};
	
	static int mapSize;
	static int[][] map;
	
	static int count;
	
	public static void main(String[] args) throws Exception {
		init();
		
		movePipe(0, 1, 0);
		
		System.out.print(count);
	}
	
	// 0: 가로 / 1: 세로 / 2: 대각
	static void movePipe(int row, int col, int direction) {
		if (row == mapSize - 1 && col == mapSize - 1) {
			count++;
			return;
		}
		
		int newRow, newCol;
		switch (direction) {
			case 0:
			case 1:
				// 해당 방향으로 직진
				newRow = row + ADD_ROW[direction];
				newCol = col + ADD_COL[direction];
				if (checkPossible(newRow, newCol, direction)) {
					movePipe(newRow, newCol, direction);
				}
				newRow = row + ADD_ROW[2];
				newCol = col + ADD_COL[2];
				// 대각으로 변환
				if (checkPossible(newRow, newCol, 2)) {
					movePipe(newRow, newCol, 2);					
				}
				break;
			// 대각
			case 2:
				// 해당 방향으로 직진
				newRow = row + ADD_ROW[2];
				newCol = col + ADD_COL[2];
				if (checkPossible(newRow, newCol, 2)) {
					movePipe(newRow, newCol, direction);					
				}
				// 가로 / 세로로 변환
				for (int index = 0; index < 2; index++) {
					newRow = row + ADD_ROW[index]; 
					newCol = col + ADD_COL[index];
					if (checkPossible(newRow, newCol, index)) {
						movePipe(newRow, newCol, index);					
					}
				}
				break;
		}
	}
	
	static boolean checkPossible(int row, int col, int direction) {
		if (row < 0 || row >= mapSize || col < 0 || col >= mapSize) {
			return false;
		}
		
		if (map[row][col] == 1) {
			return false;
		}
		
		if (direction == 2) {
			if (map[row - 1][col] == 1 || map[row][col - 1] == 1) {
				return false;
			}
		}
		return true;
	}
	
	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		mapSize = Integer.parseInt(br.readLine());
		map = new int[mapSize][mapSize];
		
		for (int row = 0; row < mapSize; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < mapSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
