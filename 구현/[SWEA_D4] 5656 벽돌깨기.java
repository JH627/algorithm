import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 공 떨어질 위치 => 순열
 * 순열을 만들 때마다 현재 맵을 복사해서 가져간다
 * 
 * 떨어트릴 위치 정하고
 * 	근데 비어있는 칸에는 던지지 않음
 * 	맵을 가져가서 블럭 부수기 => BFS
 *  남은 블럭 아래로 몰기 
 *  남은 블럭 계산, 최소값 갱신
 *   
 * 1. 공 떨어트릴 위치를 정함
 * 	1-1. 만약 해당 열에 블럭이 없다면 떨어트리지 않음
 * 	1-2. 벽돌 깨기 (BFS)
 * 		1-3. 현재 map의 값 - 1을 power로 가짐
 * 		1-4. 4방향 현재 power만큼 확인
 * 			1-4-1. 만약 map이 0인 경우
 * 				1-4-1-1. 이미 부서짐 -> queue에 넣지않음
 * 			1-4-2. 만약 map이 0이 아닌 경우
 * 				1-4-2-1. 맵이 map의 값이 1 이상 인경우
 * 					1-4-2-1-1. 현재 map의 값을 1 깎고 queue에 넣음
 * 				1-4-2-2. map의 값을 0으로 바꿈 => 방문처리
 * 
 * 	1-3. 남은 블럭 정렬
 * 		1-4. 아래서 부터 채움
 * 		1-5. 각 열의 블럭 개수도 확인
 * 	
 * 	1-4. 남은 블럭 최소값 갱신
 *
 */
public class SWEA_5656_벽돌깨기 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static final int[] ADD_ROW = {-1, 1, 0, 0};
	static final int[] ADD_COL = {0, 0, -1, 1};
	
	static class Point {
		int row, col;
		int power;
		
		public Point(int row, int col, int power) {
			this.row = row;
			this.col = col;
			this.power = power;
		}
	}
	
	static int dropCount;
	static int rowSize, colSize;
	static int[][] map;
	static int[] blockHeights;
	
	static int minBlock;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= TC; testCase++) {
			init();
			
			dropBall(map, blockHeights, 0);
			
			sb.append("#").append(testCase).append(" ").append(minBlock).append("\n");
		}
		
		System.out.print(sb);
	}

	static void dropBall(int[][] prevMap, int[] blockHeight, int ballCount) {
		int[] tempHeight = new int[colSize];
		
		for (int col = 0; col < colSize; col++) {
			if (minBlock == 0) {
				return;
			}
			
			// 해당열에 블럭이 없다면
			if (blockHeight[col] == 0) {
				continue;
			}
			
			// 맵 깊은 복사
			int[][] clonedMap = cloneMap(prevMap);

			// 해당 열 가장 맨위에 있는 블럭 부수기
			simulate(clonedMap, rowSize - blockHeight[col], col);
			// 남은 블럭 최소값 갱신
			int remainBlock = sortMap(clonedMap, tempHeight);

			minBlock = Math.min(minBlock, remainBlock);
			
			// 모든 공을 다 떨어트렸다면 종료
			if (ballCount + 1 == dropCount) {
				continue;
			}
			
			dropBall(clonedMap, tempHeight, ballCount + 1);
		}

	}
	
	static int[][] cloneMap(int[][] prevMap) {
		int[][] newMap = new int[rowSize][colSize];
		
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				newMap[row][col] = prevMap[row][col];
			}
		}
		
		return newMap;
	}
	
	static void simulate(int[][] map, int row, int col) {
		Queue<Point> toVisit = new LinkedList<>();
		
		toVisit.add(new Point(row, col, map[row][col] - 1));
		map[row][col] = 0;	
		while (!toVisit.isEmpty()) {
			Point now = toVisit.poll();
			
			int newRow, newCol;
			for (int index = 0; index < 4; index++) {
				for (int remainPower = 0; remainPower < now.power; remainPower++) {
					newRow = now.row + ADD_ROW[index] * (remainPower + 1);
					newCol = now.col + ADD_COL[index] * (remainPower + 1);
					
					if (newRow < 0 || newRow >= rowSize || newCol < 0 || newCol >= colSize) {
						break;
					}
					
					if (map[newRow][newCol] == 0) {
						continue;
					}
					
					if (map[newRow][newCol] > 1) {
						toVisit.add(new Point(newRow, newCol, map[newRow][newCol] - 1));
					}
					
					map[newRow][newCol] = 0;
				}
			}
		}
	}
	
	static int sortMap(int[][] map, int[] blockHeight) {
		int blockSum = 0;
		for (int col = 0; col < colSize; col++) {
			int writeRow = rowSize - 1;
			int count = 0;
			
			// 아래로 내리기
			for (int row = rowSize - 1; row >= 0; row--) {
			    if (map[row][col] > 0) {
			        map[writeRow--][col] = map[row][col];
			        blockSum++;
			        count++;
			    }
			}
			
			// 남은 칸 0으로 채우기
			while (writeRow >= 0) {
			    map[writeRow--][col] = 0;
			}
			
			blockHeight[col] = count;
		}
		
		return blockSum;
	}
	
	static void init() throws Exception {
		st = new StringTokenizer(br.readLine());
		
		dropCount = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		rowSize = Integer.parseInt(st.nextToken());
		
		minBlock = 0;
		map = new int[rowSize][colSize];
		blockHeights = new int[colSize];
		for (int row = 0; row < rowSize; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < colSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
				if (map[row][col] >= 1) {
					minBlock++;
					blockHeights[col]++;
				}
			}
		}
	}
}
