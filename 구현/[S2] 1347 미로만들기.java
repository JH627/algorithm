import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1347_미로만들기 {

	static BufferedReader br;
	static StringBuilder sb;
	
	// 상, 우, 하, 좌
	static final int[] ADD_ROW = {-1, 0, 1, 0};
	static final int[] ADD_COL = {0, 1, 0, -1};
	static int MAP_SIZE = 200;
	
	static int queryCount;
	static char[] query;

	static int minRow, minCol;
	static int maxRow, maxCol;
	static boolean[][] map;
	
	public static void main(String[] args) throws Exception{
		init();
		
		move();
		printMap();
		
		System.out.print(sb);
	}
	
	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		map = new boolean[MAP_SIZE][MAP_SIZE];
		queryCount = Integer.parseInt(br.readLine());
		query = br.readLine().toCharArray();
	}
	
	static void move() {
		int nowDirection = 2;
		int nowRow = 100;
		int nowCol = 100;
		
		minRow = minCol = maxRow = maxCol = 100;
		map[nowRow][nowCol] = true;
		for (int queryIndex = 0; queryIndex < queryCount; queryIndex++) {
			switch (query[queryIndex]) {
				case 'F':
					nowRow += ADD_ROW[nowDirection];
					nowCol += ADD_COL[nowDirection];
					minRow = Math.min(minRow, nowRow);
					minCol = Math.min(minCol, nowCol);
					maxRow = Math.max(maxRow, nowRow);
					maxCol = Math.max(maxCol, nowCol);
					
					map[nowRow][nowCol] = true;
					break;
				case 'L':
					nowDirection = (nowDirection + 3) % 4;
					break;
				case 'R':
					nowDirection = (nowDirection + 1) % 4;
					break;
			}
		}
	}
	
	static void printMap() {
		for (int row = minRow; row <= maxRow; row++) {
			for (int col = minCol; col <= maxCol; col++) {
				sb.append(map[row][col] ? '.' : '#');
			}
			sb.append("\n");
		}
	}
}
