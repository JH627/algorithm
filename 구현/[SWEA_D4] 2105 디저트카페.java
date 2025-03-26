package problemsss;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 시작 할 수 없는 지점
 * 행 : size - 1, size - 2 인덱스
 * 열 : 0 인덱스, size - 1 인덱스
 * 
 * 풀이
 * 
 * 오른쪽 아래, 왼쪽 아래, 왼쪽 위, 오른쪽 위 순서로 돈다.
 * 
 * 모든 케이스는 이미 선택된 디저트 번호인지 && 배열 범위를 벗어나지 않는 지 체크한 후 실행
 * (오른쪽 아래로 내려가는 경우) -> 2가지로 분기
 * 1. 오른쪽 아래로 내려 가는 경우의 수
 * 2. 방향을 전환하여 왼쪽 아래로 내려가는 경우의 수
 * 
 * (왼쪽 아래로 내려가는 경우) -> 2가지로 분기
 * 1. 왼쪽 아래로 내려 가는 경우의 수
 * 2. 방향을 전환하여 왼쪽 위로 올라가는 경우의 수
 * 
 * (왼쪽 위로 올라가는 경우) -> 반대쪽 (오른쪽 아래)이 이동한 만큼 이동해야함
 * 1. 아직 덜 이동한 경우 계속 이동
 * 2. 반대쪽이 이동한 만큼 이동한 경우 오른쪽 위로 방향 전환
 * 
 * (오른쪽 위로 올라가는 경우)
 * 1. 반대쪽 (왼쪽 아래)가 이동한 만큼의 거리가 1 만큼 남은 경우 => 원점에 도달 => 최대값 갱신
 * 2. 반대쪽 (왼쪽 아래)가 이동한 만큼의 거리가 2 이상 남은 경우 => 오른쪽위로 계속 이동
 */
public class SWEA_2105_디저트카페 {

	static BufferedReader br;
	
	// 오른쪽 아래, 왼쪽 아래, 왼쪽 위, 오른쪽 위
    static final int[] ADD_ROW = {1, 1, -1, -1};
    static final int[] ADD_COL = {1, -1, -1, 1};
    static final int MAX_NUM = 101;
	
    static int mapSize;
    static int[][] map;
	static boolean[] isSelected;
	static int maxValue;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= TC; testCase++) {
			init();
			
			// 먹을 수 없는 경우는 -1
			maxValue = -1;
			for (int row = 0; row < mapSize - 2; row++) {
				for (int col = 1; col < mapSize - 1; col++) {
					// 최소 한칸은 내려가고 시작해야하는데 내려가는 곳의 숫자와 출발 지점의 숫자가 같은 경우는 패스
					if (map[row][col] == map[row + 1][col + 1]) {
						continue;
					}
					
					// 시작할 수 없는 지점 조건을 미리 설정해놓았기 때문에 오른쪽 한칸을 무조건 내려갈 수 있음
					isSelected[map[row][col]] = true;
					isSelected[map[row + 1][col + 1]] = true;
					getMaxValue(row + 1, col + 1, 0, 1, 0, 2);
					isSelected[map[row][col]] = false;
					isSelected[map[row + 1][col + 1]] = false;
				}
			}

			sb.append("#").append(testCase).append(" ").append(maxValue).append("\n");
		}
		
		System.out.print(sb);
	}
	
	static void getMaxValue(int row, int col, int direction, 
			int oneSpan, int twoSpan, int currentValue) {
		// 오른쪽 아래로 내려가는 경우
		// 다음 칸에 있는 디저트가 이미 등장한 경우는 불가능
		if (direction == 0) {
			// 원래 가던 방향으로 가는 경우
			int nextRow = row + ADD_ROW[direction];
			int nextCol = col + ADD_COL[direction];
			
			if (isValidIndex(nextRow, nextCol) && !isSelected[map[nextRow][nextCol]]) {
				isSelected[map[nextRow][nextCol]] = true;
				getMaxValue(nextRow, nextCol, direction, 
							oneSpan + 1, twoSpan, currentValue + 1);
				isSelected[map[nextRow][nextCol]] = false;
			}
			
			// 꺾는 경우			
			nextRow = row + ADD_ROW[direction + 1];
			nextCol = col + ADD_COL[direction + 1];
				
			if (isValidIndex(nextRow, nextCol) && !isSelected[map[nextRow][nextCol]]) {
				isSelected[map[nextRow][nextCol]] = true;
				getMaxValue(nextRow, nextCol, direction + 1, 
							oneSpan, twoSpan + 1, currentValue + 1);
				isSelected[map[nextRow][nextCol]] = false;
			}
		}
		
		// 왼쪽 아래로 내려가는 경우
		// 최소 한칸은 내려간 상태
		// 내려갈때마다 분기 가능
		// 다음 칸에 있는 디저트가 이미 등장한 경우는 불가능
		else if (direction == 1) {
			// 원래 가던 방향으로 가는 경우
			int nextRow = row + ADD_ROW[direction];
			int nextCol = col + ADD_COL[direction];
			
			if (isValidIndex(nextRow, nextCol) && !isSelected[map[nextRow][nextCol]]) {
				isSelected[map[nextRow][nextCol]] = true;
				getMaxValue(nextRow, nextCol, direction, 
							oneSpan, twoSpan + 1, currentValue + 1);
				isSelected[map[nextRow][nextCol]] = false;
			}
			
			// 꺾는 경우			
			nextRow = row + ADD_ROW[direction + 1];
			nextCol = col + ADD_COL[direction + 1];
				
			if (isValidIndex(nextRow, nextCol) && !isSelected[map[nextRow][nextCol]]) {
				isSelected[map[nextRow][nextCol]] = true;
				getMaxValue(nextRow, nextCol, direction + 1, 
							oneSpan - 1, twoSpan, currentValue + 1);
				isSelected[map[nextRow][nextCol]] = false;
			}
		}

		// 왼쪽 위로 올라가는 경우
		else if (direction == 2) {
			// 반대쪽이 내려온 만큼 올라간 경우
			// 방향을 꺾기
			if (oneSpan == 0) {
				// 만약 꺾은 곳이 시작지점이라면 현재 값으로 최댓값 갱신
				if (twoSpan == 1) {
					maxValue = Math.max(maxValue, currentValue);
					return;
				}
				
				// 시작지점이 아니라면 꺾기
				int nextRow = row + ADD_ROW[direction + 1];
				int nextCol = col + ADD_COL[direction + 1];
				
				if (!isValidIndex(nextRow, nextCol) || isSelected[map[nextRow][nextCol]]) {
					return;
				}
				
				isSelected[map[nextRow][nextCol]] = true;
				getMaxValue(nextRow, nextCol, direction + 1, 
						oneSpan, twoSpan - 1, currentValue + 1);
				isSelected[map[nextRow][nextCol]] = false;
			}
			
			// 아직 반대쪽이 내려온 만큼 올라가지 못한 경우
			else {
				int nextRow = row + ADD_ROW[direction];
				int nextCol = col + ADD_COL[direction];
				
				if (!isValidIndex(nextRow, nextCol) || isSelected[map[nextRow][nextCol]]) {
					return;
				}

				isSelected[map[nextRow][nextCol]] = true;
				getMaxValue(nextRow, nextCol, direction, 
							oneSpan - 1, twoSpan, currentValue + 1);
				isSelected[map[nextRow][nextCol]] = false;
			}
		}
		
		// 오른쪽 위로 올라가는 경우
		// 원점까지 가야함
		else if (direction == 3) {
			// 반대쪽이 내려온 만큼 올라간 경우
			// 방향을 꺾기
			if (twoSpan == 1) {
				// 최대값 갱신 후 종료
				maxValue = Math.max(maxValue, currentValue);
				return;
			}
			
			// 아직 반대쪽이 내려온 만큼 올라가지 못한 경우
			else {
				int nextRow = row + ADD_ROW[direction];
				int nextCol = col + ADD_COL[direction];
				
				if (!isValidIndex(nextRow, nextCol) || isSelected[map[nextRow][nextCol]]) {
					return;
				}

				isSelected[map[nextRow][nextCol]] = true;
				getMaxValue(nextRow, nextCol, direction, 
							oneSpan, twoSpan - 1, currentValue + 1);
				isSelected[map[nextRow][nextCol]] = false;
			}	
		}
	}
	
	static boolean isValidIndex(int row, int col) {
		return row >= 0 && row < mapSize && col >= 0 && col < mapSize;
	}
	
	static void init() throws Exception {
		mapSize = Integer.parseInt(br.readLine());
		
		map = new int[mapSize][mapSize];
		isSelected = new boolean[MAX_NUM];
		
		StringTokenizer st;
		for (int row = 0; row < mapSize; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < mapSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
