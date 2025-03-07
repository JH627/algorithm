import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 풀이
 * 
 * 1. 조합 알고리즘을 통해 궁수들의 위치를 정한다
 * 2. 궁수와 적이 만날때까지 반복한다.
 * 3. 궁수들의 위치에서 가까운 병사 3명을 제거한다.
 *  3-1. 가까운 병사 = BFS로 확인
 *  3-2. 한 병사가 여러번 맞을 수 있으니 맞은것을 표시
 * 4. 궁수들의 위치를 1 올린다.
 * 5. 올린 위치에서 적을 만난다면 종료한다
 * 6. 제거한 병사 수를 출력한다.
 *
 */
public class BOJ_17135_캐슬디펜스 {

	static BufferedReader br;
	static StringTokenizer st;
	
	// 왼, 앞, 오
	static final int[] ADD_ROW = {0, -1, 0};
	static final int[] ADD_COL = {-1, 0, 1};
	
	static final int ARCHER_COUNT = 3;
	
	static int rowSize, colSize, attackDistance;
	static int[][] map;
	static int[] archerPosition;
	static int maxAttackCount, enemyCount;
	
	public static void main(String[] args) throws Exception {
		init();
		
		getArcherPosition(0, 0);
		
		System.out.print(maxAttackCount);
	}
	
	static void getArcherPosition(int elementIndex, int selectIndex) {
		if (selectIndex == ARCHER_COUNT) {
			maxAttackCount = Math.max(maxAttackCount, simulation());
			return;
		}
		
		if (elementIndex == colSize) {
			return;
		}
		
		archerPosition[selectIndex] = elementIndex;
		getArcherPosition(elementIndex + 1, selectIndex + 1);
		
		getArcherPosition(elementIndex + 1, selectIndex);
	}
	
	static int simulation() {
		int currentRow = rowSize;
		
		int time = 1;
		int removedCount = 0;
		while (true) {
			if (gameOver(currentRow, removedCount)) {
				break;
			}
			removedCount += findEnemyAndRemove(currentRow--, ++time);
			
		}
		
		clearMap();
		
		return removedCount;
	}
	
	static boolean gameOver(int currentRow, int removedCount) {
		return (removedCount == enemyCount) || (currentRow == 0);
	}
	
	static int findEnemyAndRemove(int startRow, int time) {
		int count = 0;
		
		for (int archerIndex = 0; archerIndex < ARCHER_COUNT; archerIndex++) {
			if (map[startRow - 1][archerPosition[archerIndex]] == 1) {
				map[startRow - 1][archerPosition[archerIndex]] += time;
				count++;
				continue;
			}
			else if (map[startRow - 1][archerPosition[archerIndex]] == 1 + time) {
				continue;
			}
			
			Queue<int[]> toCheck = new LinkedList<>();
			int[][] distance = new int[rowSize + 1][colSize];
			
			toCheck.add(new int[] {startRow - 1, archerPosition[archerIndex]});
			distance[startRow - 1][archerPosition[archerIndex]] = 1;
			
			
			boolean remove = false;
			while (!toCheck.isEmpty() && !remove) {
				int[] now = toCheck.poll();
				int nowRow = now[0];
				int nowCol = now[1];
				
				if (distance[nowRow][nowCol] == attackDistance) {
					continue;
				}
				
				int nextDistance = distance[nowRow][nowCol] + 1;
				for (int deltaIndex = 0; deltaIndex < 3; deltaIndex++) {
					int newRow = nowRow + ADD_ROW[deltaIndex];
					int newCol = nowCol + ADD_COL[deltaIndex];
					
					if (isInvalidIndex(newRow, newCol)) {
						continue;
					}
					
					if (map[newRow][newCol] == 1) {
						map[newRow][newCol] += time;
						remove = true;
						count++;
						break;
					}
					else if (map[newRow][newCol] == 1 + time) {
						remove = true;
						break;
					}
					else {
						if (distance[newRow][newCol] != 0) {
							continue;
						}
						
						distance[newRow][newCol] = nextDistance;
						toCheck.add(new int[] {newRow, newCol});
					}
				}
			}			
		}
		
		return count;
	}
	
	static boolean isInvalidIndex(int row, int col) {
		return row < 0 || row >= rowSize + 1 || col < 0 || col >= colSize; 
	}
	
	static void clearMap() {
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				if (map[row][col] > 1) {
					map[row][col] = 1;
				}
			}
		}
	}
	
	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		attackDistance = Integer.parseInt(st.nextToken());
		
		map = new int[rowSize + 1][colSize];
		archerPosition = new int[ARCHER_COUNT];
		
		enemyCount = 0;
		for (int row = 0; row < rowSize; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < colSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
				if (map[row][col] == 1) {
					enemyCount++;
				}
			}
		}
		
		maxAttackCount = 0;
	}
}
