import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 풀이
 * 
 * 1. 농작물들의 포인트를 입력받는다.
 * 2. BFS를 통하여 중앙에서부터 농작물들을 방문하며 포인트들을 더한다.
 * 2-1. 거리가 mapSize / 2 + 1 인 곳까지의 농작물들을 방문하며 점수를 더한다.
 * 2-1. 이전에 방문했던 곳은 탐색하지 않는다.
 *
 */
public class SWEA_2805_농작물수확하기 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static final int[] ADD_ROW = {-1, 0, 1, 0};
	static final int[] ADD_COL = {0, -1, 0, 1};
	
	static int mapSize; // 맵의 크기
	static int[][] points; // 농작물들의 점수

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= TC; testCase++) {
			init();
			
			int point = getPointFromArea(mapSize / 2, mapSize / 2);

			sb.append("#").append(testCase).append(" ").append(point).append("\n");
		}

		System.out.print(sb);
	}
	
	static int getPointFromArea(int centerRow, int centerCol) {
		int pointSum = 0; // 포인트들을 저장할 변수
		
		int[][] distance = new int[mapSize][mapSize]; // 농작물의 거리를 저장할 배열
		distance[centerRow][centerCol] = 1; // 시작지점(중앙)은 거리 1로 초기화
		
		Queue<int[]> toVisit = new LinkedList<>();
		toVisit.add(new int[] {centerRow, centerCol});
		while (!toVisit.isEmpty()) {
			int[] now = toVisit.poll();
			int nowRow = now[0];
			int nowCol = now[1];
			
			// 방문한 곳의 점수를 합에 더한다
			pointSum += points[nowRow][nowCol];
			
			// 만약 거리가 수확 최대 거리에 도달한 경우 다른 곳 탐색
			if (distance[nowRow][nowCol] == mapSize / 2 + 1) {
				continue;
			}
			
			// 인근에 있는 수확물들을 확인하며 방문하지 않았다면 방문 예정 처리
			for (int addIndex = 0; addIndex < 4; addIndex++) {
				int newRow = nowRow + ADD_ROW[addIndex];
				int newCol = nowCol + ADD_COL[addIndex];
				
				// 중앙에서 시작하기 때문에 거리제한을 두는 순간 밖으로 나가는 경우는 없음
//				if (newRow < 0 || newRow >= mapSize || newCol < 0 || newCol >= mapSize) {
//					continue;
//				}
				
				// 만약 방문한 곳이라면 추가로 방문하지 않음
				if (distance[newRow][newCol] != 0) {
					continue;
				}
				
				// 다음 농작물의 거리는 현재 농작물의 거리 + 1
				distance[newRow][newCol] = distance[nowRow][nowCol] + 1;
				toVisit.add(new int[] {newRow, newCol});				
			}
		}
		
		return pointSum;
	}

	static void init() throws IOException {
		mapSize = Integer.parseInt(br.readLine());
		points = new int[mapSize][mapSize];
		
		for (int row = 0; row < mapSize; row++) {
			String pointString = br.readLine();
			for (int col = 0; col < mapSize; col++) {
				points[row][col] = pointString.charAt(col) - '0';
			}
		}
	}
	
}
