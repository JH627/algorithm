import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 1. 치킨집의 위치와과 시작지점을 구분해서 입력을 받는다.
 * 2. 사람과 치킨 집의 거리를 미리 계산한다. 
 * 3. 치킨집 개수의 제한을 기준으로 선택 가능한 치킨집의 경우의 수를 모두 확인한다.
 * 4. 집들과 치킨집의 거리를 계산하여 최소 거리를 갱신한다.
 * 4-1. 만약 거리 계산 중 현재 최소 거리보다 긴 경우에는 계산 중단
 *
 */
public class BOJ_15686_치킨배달 {

	static BufferedReader br;
	static StringTokenizer st;

	static final int[] ADD_ROW = {-1, 0, 1, 0};
	static final int[] ADD_COL = {0, -1, 0, 1};
	
	static class Position {
		int row, col;

		public Position(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	static int mapSize, limit, storeCount, startCount;
	static ArrayList<Position> startingPoints, storePoints;
	static int[][] dist; // 사람별 치킨집 거리
	static int[] selectedStore;
	static int minDistance = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		init();
		
		// 사람별 치킨집 거리 계산
		getDistance();
		
		selectedStore = new int[storeCount];
		// 치킨집 조합을 구함
		getSelectedStore(0, 0);
		
		System.out.print(minDistance);
	}
	
	static void getDistance() {
		dist = new int[startCount][storeCount];
		
		for (int startPoint = 0; startPoint < startCount; startPoint++) {
			setDistance(startPoint);
		}
	}
	
	static void setDistance(int start) {
		Position startPoint = startingPoints.get(start);
		
		for (int storeIndex = 0; storeIndex < storeCount; storeIndex++) {
			Position storePoint = storePoints.get(storeIndex);
			dist[start][storeIndex] = Math.abs(startPoint.row - storePoint.row) + Math.abs(startPoint.col - storePoint.col);
		}
	}
	
	static void getSelectedStore(int storeIndex, int selectedIndex) {
		// 조합이 구해진 경우 거리 합 확인
		if (selectedIndex == limit) {
			minDistance = Math.min(minDistance, checkDistance());
			return;
		}
		
		if (storeIndex == storeCount) {
			return;
		}
		
		selectedStore[selectedIndex] = storeIndex;
		getSelectedStore(storeIndex + 1, selectedIndex + 1);
		
		// 남은걸로 limit을 채울 수 없는 경우 
		if (storeCount - 1 - storeIndex >= limit - selectedIndex) {
			getSelectedStore(storeIndex + 1, selectedIndex);
		}
	}
	
	// 집들과 치킨집의 거리 계산
	static int checkDistance() {
		int sum = 0;
		for (int startIndex = 0; startIndex < startingPoints.size(); startIndex++) {
			int tempDistance = dist[startIndex][selectedStore[0]];
			for (int index = 1; index < limit; index++) {
				tempDistance = Math.min(tempDistance, dist[startIndex][selectedStore[index]]);
			}
			
			sum += tempDistance;
			// 현재 최소 거리를 넘는 경우 더 이상 탐색할 필요 없음
			if (sum > minDistance) {
				return Integer.MAX_VALUE;
			}
		}
		
		return sum;
	}
	
	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		mapSize = Integer.parseInt(st.nextToken());
		limit = Integer.parseInt(st.nextToken());
		
		startingPoints = new ArrayList<>();
		storePoints = new ArrayList<>();
		for (int row = 0; row < mapSize; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < mapSize; col++) {
				int value = Integer.parseInt(st.nextToken());
				switch (value) {
					case 1:
						// 시작지점
						startingPoints.add(new Position(row, col));
						break;
					case 2:
						// 치킨 집
						storePoints.add(new Position(row, col));
						break;
				}
			}
		}
		
		storeCount = storePoints.size();
		startCount = startingPoints.size();
	}
		
}
