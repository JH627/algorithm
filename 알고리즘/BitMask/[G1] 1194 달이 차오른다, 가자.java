import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 알파벳이 6개이므로 비트에 현재상태를 저장한채로 이동해보자
 * 
 * 1. 시작지점에서 이동 시작
 * 
 * 2. 이동
 * 	2-1. 탈출 지점인 경우
 * 		2-1-1. 현재까지의 거리 + 1을 반환하고 종료한다.
 * 	2-2. 빈칸(빈칸, 출발 지점)인 경우
 * 		2-2-1. 만약 현재 키 상태로 다음 노드에 방문한 적이 있다면 이동하지 않는다
 * 		2-2-2. 아닌 경우 현재까지의 거리 + 1을 하고 이동한다. 
 * 	2-3. 대문자인 경우
 * 		2-3-1. 만약 현재 키 상태로 다음 노드에 방문한 적이 있다면 이동하지 않는다
 * 		2-3-2. 문을 열 수 있는 키를 가지고 있다면 이동한다.
 * 	2-4. 소문자인 경우
 * 		2-4-1. 현재 키상태를 최신화하고 이동한다
 * 		2-4-2. 만약 최신화된 키 상태로 다음 노드에 방문한 적이 있다면 이동하지 않는다
 * 	2-5. 빈칸인 경우
 * 		2-5-1. 만약 현재 키 상태로 다음 노드에 방문한 적이 있다면 이동하지 않는다
 *
 */
public class BOJ_1194_달이차오른다가자 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static final int[] ADD_ROW = {-1, 0, 1, 0};
	static final int[] ADD_COL = {0, 1, 0, -1};
	
	static class Point {
		int row, col;
		int distance;
		int keyStatus;
		
		public Point(int row, int col, int distance, int keyStatus) {
			this.row = row;
			this.col = col;
			this.distance = distance;
			this.keyStatus = keyStatus;
		}
	}
	
	static int rowSize, colSize;
	static int startRow, startCol;
	static char[][] map;
	
	public static void main(String[] args) throws Exception {
		init();
		
		System.out.print(getMinDistance());
	}
	
	static int getMinDistance() {
		boolean[][][] visited = new boolean[rowSize][colSize][(1 << 6)];
		
		Queue<Point> toVisit = new LinkedList<>();
		toVisit.add(new Point(startRow, startCol, 0, 0));
		visited[startRow][startCol][0] = true;
		
		int newRow, newCol;
		while (!toVisit.isEmpty()) {
			Point now = toVisit.poll();
			
			for (int index = 0; index < 4; index++) {
				newRow = now.row + ADD_ROW[index];
				newCol = now.col + ADD_COL[index];
				
				// 맵 밖이거나 벽인 경우
				if (newRow < 0 || newRow >= rowSize || newCol < 0 || newCol >= colSize || map[newRow][newCol] == '#') {
					continue;
				}
				
				// 탈출 지점인 경우
				if (map[newRow][newCol] == '1') {
					return now.distance + 1;
				}
				
				// 빈 칸인 경우 (빈칸, 출발 지점)
				if (map[newRow][newCol] == '.' || map[newRow][newCol] == '0') {
					if (visited[newRow][newCol][now.keyStatus]) {
						continue;
					}
					visited[newRow][newCol][now.keyStatus] = true;
					toVisit.add(new Point(newRow, newCol, now.distance + 1, now.keyStatus));
				}
				
				// 대문자인 경우 (문인 경우)
				if (Character.isUpperCase(map[newRow][newCol])) {
					// 열쇠가 있다면
					if ((now.keyStatus & (1 << (map[newRow][newCol] - 'A'))) >= 1) {
						// 방문한 경우는 제외
						if (visited[newRow][newCol][now.keyStatus]) {
							continue;
						}
						visited[newRow][newCol][now.keyStatus] = true;
						toVisit.add(new Point(newRow, newCol, now.distance + 1, now.keyStatus));
					}
				}
				
				// 소문자인 경우 (열쇠인 경우)
				if (Character.isLowerCase(map[newRow][newCol])) {
					// 열쇠 상태 최신화
					int newStatus = now.keyStatus | (1 << (map[newRow][newCol] - 'a'));
					if (visited[newRow][newCol][newStatus]) {
						continue;
					}
					
					visited[newRow][newCol][newStatus] = true;
					toVisit.add(new Point(newRow, newCol, now.distance + 1, newStatus));
				}
			}
		}
		
		return -1;
	}
	
	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		
		map = new char[rowSize][colSize];
		for (int row = 0; row < rowSize; row++) {
			String line = br.readLine();
			for (int col = 0; col < colSize; col++) {
				map[row][col] = line.charAt(col);
				if (map[row][col] == '0') {
					startRow = row;
					startCol = col;
				}
			}
		}
	}
}
