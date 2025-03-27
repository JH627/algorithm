import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 1. 카메라의 위치와 종류를 입력받는다.
 * 2. 카메라가 확인할 수 있는 경우의 수를 모두 확인해본다.
 * 2-1. 만약 최소 크기 0을 발견했다면 종료
 * 2-2. 만약 카메라의 방향을 모두 결정했다면, 최소 사각지대 크기 최신화 후 종료
 * 3. 결정된 카메라의 방향에 따라 맵을 갱신한다.
 * 
 * 경우의 수
 * 4 / 2 / 4 / 4 / 1 개
 * 
 * 5번 2번을 먼저 처리
 * 
 */
public class BOJ_15683_감시 {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	// 좌, 상, 우 , 하
	static final int[] ADD_ROW = {0, -1, 0, 1};
	static final int[] ADD_COL = {-1, 0, 1, 0};
	
	static class Camera implements Comparable<Camera> {
		int row, col, type;

		public Camera(int row, int col, int type) {
			this.row = row;
			this.col = col;
			this.type = type;
		}

		@Override
		public int compareTo(Camera o) {
			return o.type - this.type;
		}	
	}
	
	static int rowSize, colSize;
	static int emptyArea;
	static int[][] map;
	
	static ArrayList<Camera> cams; // 카메라의 위치, 종류
	static int camCount;
	static int minSize, clock;

	public static void main(String[] args) throws Exception {
		init();
		
		getAllMapStatusAndFindMinSize(0, 0);
		
		System.out.print(minSize);
	}
	
	static void getAllMapStatusAndFindMinSize(int selected, int removedCount) {
		// 만약 최소 크기 0을 발견했다면 종료
		if (removedCount == emptyArea || minSize == 0) {
			minSize = 0;
			return;
		}
		
		// 만약 카메라의 상태를 모두 결정했다면
		// 최소 사각지대 크기 최신화
		if (selected == camCount) {			
			minSize = Math.min(minSize, emptyArea - removedCount);
			return;
		}
		
		Camera camera = cams.get(selected);
		int row = camera.row;
		int col = camera.col;
		
		// 1번
		if (camera.type == 1) {
			int count = 0;
			// 좌, 상, 우 , 하
			for (int direction = 0; direction < 4; direction++) {
				count = update(row, col, direction, clock++);
				getAllMapStatusAndFindMinSize(selected + 1, removedCount + count);
				remove(row, col, direction, --clock);
			}
		}
		
		// 2번 (정렬을 위해 4번 타입이 2번 타입을 의미)
		if (camera.type == 4) {
			int count = 0;
			// 좌 우
			count = update(row, col, 0, clock++);
			count += update(row, col, 2, clock++);
			getAllMapStatusAndFindMinSize(selected + 1, removedCount + count);
			remove(row, col, 2, --clock);
			remove(row, col, 0, --clock);

			// 상 하
			count = update(row, col, 1, clock++);
			count += update(row, col, 3, clock++);
			getAllMapStatusAndFindMinSize(selected + 1, removedCount + count);
			remove(row, col, 3, --clock);
			remove(row, col, 1, --clock);
		}
		
		// 3번
		if (camera.type == 3) {
		    for (int direction = 0; direction < 4; direction++) {
		        int count = 0;
		        int dir1 = direction;
		        int dir2 = (direction + 1) % 4;
		        
		        count += update(row, col, dir1, clock++);
		        count += update(row, col, dir2, clock++);
		        
		        getAllMapStatusAndFindMinSize(selected + 1, removedCount + count);
		        
		        remove(row, col, dir2, --clock);
		        remove(row, col, dir1, --clock);
		    }
		}

		
		// 4번
		if (camera.type == 2) {
			int count = 0;
			// 위뾰족, 오른뾰족, 아래 뾰족, 왼뾰족
			for (int direction = 0; direction < 4; direction++) {
				count = 0;
				for (int dir = 0; dir < 4; dir++) {
					if (dir != direction) {
						count += update(row, col, dir, clock++);
					}
				}
				
				getAllMapStatusAndFindMinSize(selected + 1, removedCount + count);
				
				for (int dir = 3; dir >= 0; dir--) {
					if (dir != direction) {
						remove(row, col, dir, --clock);
					}
				}
			}
		}
		
		// 5번
		if (camera.type == 5) {
			int count = 0;
			// 4방향 다
			for (int direction = 0; direction < 4; direction++) {
				count += update(row, col, direction, clock++);
			}
			getAllMapStatusAndFindMinSize(selected + 1, removedCount + count);
			for (int direction = 3; direction >= 0; direction--) {
				remove(row, col, direction, --clock);
			}
		}
		
	}
	
	static int update(int row, int col, int direction, int clock) {
		int count = 0;
		
		while (true) {
			row = row + ADD_ROW[direction];
			col = col + ADD_COL[direction];
			
			if (row < 0 || row >= rowSize || col < 0 || col >= colSize) {
				break;
			}
			
			// 벽은 막힘
			if (map[row][col] == 6) {
				break;
			}
			
			// 안바뀐 부분만 바꿔보자
			if (map[row][col] == 0) {
				map[row][col] = clock;
				count++;
			}
		}
		
		return count;
	}
	
	static void remove(int row, int col, int direction, int clock) {
		while (true) {
			row = row + ADD_ROW[direction];
			col = col + ADD_COL[direction];
			
			if (row < 0 || row >= rowSize || col < 0 || col >= colSize) {
				break;
			}
			
			// 벽은 막힘
			if (map[row][col] == 6) {
				break;
			}
			
			// 바뀐 부분 복구
			if (map[row][col] == clock) {
				map[row][col] = 0;
			}
		}
	}
	
	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		
		cams = new ArrayList<>();
		camCount = 0;
		
		emptyArea = rowSize * colSize;
		map = new int[rowSize][colSize];
		for (int row = 0; row < rowSize; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < colSize; col++) {
				int value = Integer.parseInt(st.nextToken());
				if (value == 6) {
					emptyArea--;
					map[row][col] = 6;
					continue;
				}
				if (value != 0) {
					map[row][col] = value;
					emptyArea--;
					if (value == 4) {
						value = 2;
					}
					else if (value == 2) {
						value = 4;
					}
					cams.add(new Camera(row, col, value));
					camCount++;
				}
			}	
		}
		
		minSize = Integer.MAX_VALUE;
		
		Collections.sort(cams);
		
		clock = 100;
	}

}
