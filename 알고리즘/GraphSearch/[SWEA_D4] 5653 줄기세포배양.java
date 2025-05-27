import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 세포를 시간 순으로 정렬
 * 시간이 같다면 생명력 수치가 높은 순 정렬
 * => 생명력 높은 세포가 먼저 점령하게 하기 위함
 * 
 * 배양시간이 300, 처음에 주어지는 셀의 위치가 1<=위치<=50 이므로
 * 배열을 700으로 넉넉하게 잡으면 세포가 범위를 넘어가지 않음
 *
 * 현재 있는 세포들을 모두 PQ 비활성 상태로 넣고 진행
 * =======================
 * 
 * 1. 세포가 비활성 상태인 경우
 * 	1-1. 만약 현재 칸이 이미 점령당한 경우 => 같은 시간에 생명력이 더 높은 세포가 이미 차지함
 * 		1-1-1. 아무것도 하지않음
 * 	1-2. 현재 칸이 점령당하지 않은 상태인 경우
 * 		1-2-1. 점령한 세포 칸 개수++
 * 		1-2-2. 현재 시간 + 생명력 수치 시간에 활성상태가 되도록 PQ에 등록
 * 
 * 2. 세포가 활성상태인 경우
 * 	2-1. 4방향으로 퍼짐
 * 		2-1-1. 만약 이미 점령당한 칸이면 퍼지지 않음
 * 		2-1-2. 점령 안 당한 경우
 * 			2-1-2-1. 비어있는 칸에 현재 시간 + 1에 비활성 세포가 되도록 PQ에 등록
 * 	2-2. 현재 시간 + 생명력 수치 시간에 죽음 상태가 되도록 PQ에 등록
 * 
 * 3. 세포가 죽은 상태인 경우
 * 	3-1. 죽은 세포 개수++
 *
 * ========================
 *
 * 4. 점령된 개수 - (죽은 상태) == (활성 + 비활성) 출력
 *
 */
public class SWEA_5653_줄기세포배양 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static final int[] ADD_ROW = {-1, 1, 0, 0};
	static final int[] ADD_COL = {0, 0, -1, 1};
	
	static final int SALT = 310; // (0, 0) -> (310, 310)
	static final int MAP_SIZE = 700;
	
	static final int DEAD = 0;
	static final int ACTIVE = 1;
	static final int INACTIVE = 2;
	
	static class Cell implements Comparable<Cell> {
		int row, col;
		int status;
		int usedTime, power; // 쿼리 시간, 생명력 수치
		
		public Cell(int row, int col, int status, int usedTime, int power) {
			this.row = row;
			this.col = col;
			this.status = status;
			this.usedTime = usedTime;
			this.power = power;
		}

		@Override
		public int compareTo(Cell o) {
			if (this.usedTime == o.usedTime) {
				// 시간이 같으면 생명력 수치가 높은 순
				return o.power - this.power;				
			}
			return this.usedTime - o.usedTime;
		}
	}
	
	static int timeLimit;
	static int rowSize, colSize;
	static boolean[][] filled;
	
	static PriorityQueue<Cell> cells;
	static int cellCount, deadCellCount;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= TC; testCase++) {
			init();
			
			simulate();
			
			// 점령된 개수 - (죽은 상태) == 활성 + 비활성
			sb.append("#").append(testCase).append(" ").append(cellCount - deadCellCount).append("\n");
		}
		
		System.out.print(sb);
	}
	
	static void simulate() {
		
		while (!cells.isEmpty()) {
			Cell now = cells.poll();
			
			// K시간 후까지 체크 완료
			if (now.usedTime > timeLimit) {
				break;
			}

			// 비활성 상태
			if (now.status == INACTIVE) {
				if (filled[now.row][now.col]) {
					continue;
				}
				filled[now.row][now.col] = true;
				cellCount++;
				
				// 다음 활성 상태시간 맞춰 등록
				cells.add(new Cell(now.row, now.col, ACTIVE, now.usedTime + now.power, now.power));
			}
			// 활성 상태
			else if (now.status == ACTIVE) {
				int newRow, newCol;
				for (int index = 0; index < 4; index++) {
					newRow = now.row + ADD_ROW[index];
					newCol = now.col + ADD_COL[index];
					
					if (filled[newRow][newCol]) {
						continue;
					}
					
					// 비어있는 칸에 비활성 셀 할당
					cells.add(new Cell(newRow, newCol, INACTIVE, now.usedTime + 1, now.power));
				}
				
				// 다음 죽은 상태 시간 맞춰 등록
				cells.add(new Cell(now.row, now.col, DEAD, now.usedTime + now.power, now.power));
			}
			else if (now.status == DEAD) {
				deadCellCount++;
			}
		}
		
	
	}
	
	static void init() throws Exception {
		cells = new PriorityQueue<>();
		
		st = new StringTokenizer(br.readLine());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		timeLimit = Integer.parseInt(st.nextToken());
		
		cellCount = 0;
		deadCellCount = 0;
		filled = new boolean[MAP_SIZE][MAP_SIZE];
		for (int row = 0 ; row < rowSize; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < colSize; col++) {
				int power = Integer.parseInt(st.nextToken());
				if (power > 0) {
					cells.add(new Cell(row + SALT, col + SALT, INACTIVE, 0, power));
				}
			}
		}
	}
}
