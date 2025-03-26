import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 풀이
 * 
 * 1. 현재 방문하지 않은 곳 이라면 그래프 탐색을 시작한다 => 분리된 공간 ++ 
 * 2. 탐색 시
 * 2-1. 주변색과 현재 색이 같다면 앞으로 방문할 곳에 추가
 * 2-2. 주변색과 현재 색이 다르지만, 만약 적록색약이라면 
 *      (현재 R && 방문할 곳 G) 또는 (현재 G && 방문할 곳 R)인 경우 방문할 곳에 추가
 * 3. 그래프 탐색을 시작한 횟수를 출력
 * 
 */
public class BOJ_10026_적록색약 {

	static BufferedReader br;

    static final int[] ADD_ROW = {1, -1, 0, 0};
    static final int[] ADD_COL = {0, 0, 1, -1};
	
	static int mapSize;
	static char[][] color; // 그림
	static boolean[][] visited; // 방문 상태
	
	public static void main(String[] args) throws Exception {
		init();

		// 색약이 아닌 경우 그래프 탐색
		int normal = getAreaCount(true);
		// 색약인 경우 그래프 탐색
		int loose = getAreaCount(false); 
		
		System.out.printf("%d %d", normal, loose);
	}
	
	static int getAreaCount(boolean isNormal) {
		visited = new boolean[mapSize][mapSize];
		
		// 그래프 탐색을 시작한 횟수
		int count = 0;
		for (int row = 0; row < mapSize; row++) {
			for (int col = 0; col < mapSize; col++) {
				if (visited[row][col]) {
					continue;
				}
				visited[row][col] = true;
				count++;
			
				checkNearNode(row, col, isNormal);
			}
		}
		
		return count;
	}
	
	static void checkNearNode(int row, int col, boolean isNormal) {
		Queue<int[]> toVisit = new LinkedList<>();
		
		toVisit.add(new int[] {row, col});
		visited[row][col] = true;
		
		int nowRow, nowCol, newRow, newCol;
		while (!toVisit.isEmpty()) {
			int[] now = toVisit.poll();
			nowRow = now[0];
			nowCol = now[1];
			
			for (int index = 0; index < 4; index++) {
				newRow = nowRow + ADD_ROW[index];
				newCol = nowCol + ADD_COL[index];
				
				if (newRow < 0 || newRow >= mapSize || newCol < 0 || newCol >= mapSize) {
					continue;
				}
				
				if (visited[newRow][newCol]) {
					continue;
				}
				
				// 일반 && 적록색약이 같은 색이라고 느끼는 경우
				if (color[nowRow][nowCol] == color[newRow][newCol]) {
					visited[newRow][newCol] = true;
					toVisit.add(new int[] {newRow, newCol});
				}
				
				if (isNormal) {
					continue;
				}
				
				// 적록색약이 같은 색이라고 느끼는 케이스
				// (현재 R && 방문할 곳 G) 또는 (현재 G && 방문할 곳 R)
				if ((color[nowRow][nowCol] == 'R' && color[newRow][newCol] == 'G') ||
		            		(color[nowRow][nowCol] == 'G' && color[newRow][newCol] == 'R')) {
					visited[newRow][newCol] = true;
					toVisit.add(new int[] {newRow, newCol});
				}
			}
		}
		
	}
	
	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		mapSize = Integer.parseInt(br.readLine());
		
		color = new char[mapSize][mapSize];
		for (int row = 0; row < mapSize; row++) {
			String colorLine = br.readLine();
			for (int col = 0; col < mapSize; col++) {
				color[row][col] = colorLine.charAt(col);
			}
		}
	}
}
