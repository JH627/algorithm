import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 풀이
 * 
 * 1. 최소 맛 - 1 부터 최대 맛까지 상황을 가정하고 탐색
 * 2. 배열을 모두 확인하며 (만약 기준 맛 보다 큰 경우) && (아직 확인하지 않은 지역일 경우) 그래프 탐색 시작
 * 3. 그래프 탐색을 시작한 지점의 개수가 덩어리의 개수
 * 4. 탐색을 마치면 최대 덩어리 개수를 갱신
 *
 */
public class SWEA_7733_치즈도둑 {
	
	static BufferedReader br;

	static final int[] ADD_ROW = {-1, 1, 0, 0};
	static final int[] ADD_COL = {0, 0, -1, 1};
	
	static int mapSize;
	static int[][] map; // 치즈 상태
	static boolean[][] visited; // 확인한 치즈
	static int minTaste, maxTaste; // 최대, 최소 맛
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= TC; testCase++) {
			init();
			
			int maxAreaCount = 0;
			// 최소 맛 - 1 부터 최대 맛까지 상황을 가정하고 탐색
			for (int nowTaste = minTaste - 1; nowTaste < maxTaste; nowTaste++) {
				maxAreaCount = Math.max(maxAreaCount, getAreaCount(nowTaste));
			}
			
			sb.append("#").append(testCase).append(" ").append(maxAreaCount).append("\n");
		}
		
		System.out.print(sb);
	}
	
	static int getAreaCount(int nowTaste) {
		visited = new boolean[mapSize][mapSize];
		
		// 덩어리 개수
		int count = 0;
		for (int row = 0; row < mapSize; row++) {
			for (int col = 0; col < mapSize; col++) {
				if (visited[row][col]) {
					continue;
				}
				
				// 만약 맛이 기준보다 낮다면 패스
				if (map[row][col] <= nowTaste) {
					continue;
				}
				
				count++;
				// 이어진 덩어리 체크
				checkNearArea(row, col, nowTaste);
			}
		}

		return count;
	}
	
	static void checkNearArea(int row, int col, int nowTaste) {
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
				
				// 맛이 기준보다 낮다면 패스
				if (map[newRow][newCol] <= nowTaste) {
					continue;
				}
				
				visited[newRow][newCol] = true;
				toVisit.add(new int[] {newRow, newCol});
			}
		}
	}
	
	static void init() throws Exception {
		mapSize = Integer.parseInt(br.readLine());
		map = new int[mapSize][mapSize];

		minTaste = 101; maxTaste = 0;
		StringTokenizer st;
		for (int row = 0; row < mapSize; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < mapSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
				// 맛의 최소, 최대 갱신
				minTaste = Math.min(minTaste, map[row][col]);
				maxTaste = Math.max(maxTaste, map[row][col]);
			}
		}
	}
}
