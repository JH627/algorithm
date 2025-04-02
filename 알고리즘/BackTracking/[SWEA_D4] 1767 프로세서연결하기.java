import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 풀이
 * 
 * 1. 입력을 받는다
 * 1-1. 만약 Core인경우 (map[row][col] == 1)
 * 1-1-1. 만약 가장자리쪽인 경우 반드시 연결이 가능한 Core에 추가
 * 1-1-2. 가장자리쪽이 아닌 경우 확인해볼 Core목록 (startingPoint)에 추가
 * 
 * 2. 각 Core의 방향을 정하고 가장자리와 연결되는 지 확인
 * 2-1. 만약 모든 Core의 방향을 확인했다면 최대 Core개수와, 최소 전선 길이 갱신
 * 
 * 2-2. 연결 확인
 * 2-2-1. 만약 연결이 안된다면 
 * 2-2-1-1. 방문처리 원상복구
 * 2-2-1-2. 연결된 Core개수에 포함하지 않고 다른 Core 확인
 * 
 * 2-2-2. 만약 연결이 됐다면
 * 2-2-2-1. 방문처리
 * 2-2-2-2. 연결된 Core개수에 포함하고 다른 Core확인
 * 2-2-2-3. 방문처리 원상복구
 *
 */
public class SWEA_1767_프로세서연결하기 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static class Point {
		int row, col;

		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	// 상, 하, 좌, 우
	static final int[] ADD_ROW = {-1, 1, 0, 0};
	static final int[] ADD_COL = {0, 0, -1, 1};
	
	static int mapSize;
	static int[][] map;
	static ArrayList<Point> startingPoints;
	
	static int maxConnected; // 연결된 Core의 최대 개수
	static int minLength; // 전선의 최소 길이
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= TC; testCase++) {
			init();
			
			setDirection(0, maxConnected, 0);

			sb.append("#").append(testCase).append(" ").append(minLength).append("\n");
		}
		
		System.out.print(sb);
	}
	
	static void setDirection(int pointIndex, int connected, int lenSum) {
		// 모든 Core를 다 확인한 경우
		if (pointIndex == startingPoints.size()) {
			// Core개수가 최대인 경우
			if (maxConnected < connected) {
				maxConnected = connected;
				minLength = lenSum;
			}
			// 최대 코어 개수와 같은경우 
			// 최소 길이 갱신
			else if (maxConnected == connected) {
				minLength = Math.min(minLength, lenSum);
			}
			return;
			
		}
		
		// 남은 걸 다 연결해도 최대 개수를 갱신할 수 없는 경우
		// 최대 개수 > 현재 선택된 개수 + 남은 선택지 
		if (maxConnected > connected + startingPoints.size() - pointIndex) {
			return;
		}
		
		for (int direction = 0; direction < 4; direction++) {
			int len = checkPossible(pointIndex, direction);
			// 가능한 경우
			if (len != -1) {
				// 다른 Core의 방향을 확인
				setDirection(pointIndex + 1, connected + 1, lenSum + len);
				
				// 원복
				restore(pointIndex, direction, len);
			}
			// 불가능 한 경우
			else {
				setDirection(pointIndex + 1, connected, lenSum);
			}
		}
	}
	
	static int checkPossible(int pointIndex, int direction) {
		int startPointRow = startingPoints.get(pointIndex).row;
		int startPointCol = startingPoints.get(pointIndex).col;
		
		int nowRow = startPointRow;
		int nowCol = startPointCol;
		
		int len = 0;
		while (true) {
			nowRow += ADD_ROW[direction];
			nowCol += ADD_COL[direction];
			
			// 가장 자리에 도달한 경우
			if (nowRow < 0 || nowRow >= mapSize || nowCol < 0 || nowCol >= mapSize) {
				return len;
			}
			
			len++;
			
			// 다른 전선이 있는경우
			if (map[nowRow][nowCol] == 1) {
				// 방향을 반대로 뒤집어서
				direction = (direction % 2) == 1 ? direction - 1 : direction + 1;
				// 방문처리 취소
				while (true) {
					nowRow += ADD_ROW[direction];
					nowCol += ADD_COL[direction];
					
					if (nowRow == startPointRow && nowCol == startPointCol) {
						return -1;
					}

					map[nowRow][nowCol] = 0;
				}
			}

			// 방문처리
			map[nowRow][nowCol] = 1;
		}	
	}
	
	// 연결된 길이 만큼 다시 복구
	static void restore(int pointIndex, int direction, int len) {
		int nowRow = startingPoints.get(pointIndex).row;
		int nowCol = startingPoints.get(pointIndex).col;
		
		for (int index = 0; index < len; index++) {
			nowRow += ADD_ROW[direction];
			nowCol += ADD_COL[direction];
			
			map[nowRow][nowCol] = 0;
		}
	}
	
	static void init() throws Exception {
		mapSize = Integer.parseInt(br.readLine());
		
		maxConnected = 0;
		minLength = 0;
		startingPoints = new ArrayList<>();
		map = new int[mapSize][mapSize];
		for (int row = 0; row < mapSize; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < mapSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
				if (map[row][col] == 1) {
					// 가장자리 쪽인 경우
					if (row == 0 || row == mapSize - 1 || col == 0 || col == mapSize - 1) {
						maxConnected++;
					}
					// 중앙쪽인 경우
					else {
						startingPoints.add(new Point(row, col));
					}
				}
			}
		}
	}
}
