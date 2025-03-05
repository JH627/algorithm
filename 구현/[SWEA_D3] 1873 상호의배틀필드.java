import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 구현
 * 
 * 풀이
 * 
 * 1. 전차의 현재 위치와, 바라보는 방향을 보관하고 행동마다 최신화한다.
 * 2. 다음 전차에 행동에 따라 맵의 상태를 바꾼다.
 * 2-1. 포탄을 발사하는 경우
 *      => 바라보는 방향을 쭉 확인하며 맵끝에 도달하거나 벽을 만난경우 중지, 만약 벽돌 벽인 경우 평지로 변경 후 종료
 * 2-2. 이동하는 경우
 *      => 우선 방향을 바꾸고, 갈 수 있는 곳인경우(평지만) 전차를 이동시킨다.
 *
 */
public class SWEA_1873_상호의배틀필드 {
	
	static BufferedReader br;
	static StringBuilder sb;
	
	// Up, Down, Left, Right
	// 방향에 따른 탱크 모양, 명령어, 이동방향
	static final char[] TANK_VIEW = {'^', 'v', '<', '>'};
	static final char[] DIRECTIONS = {'U', 'D', 'L', 'R'};
	static final int[] ADD_ROW = {-1, 1, 0, 0};
	static final int[] ADD_COL = {0, 0, -1, 1};
		
	static String queries; // 명령
	static char[][] map; // 현재 맵 상태
	static int mapRowSize, mapColSize; // 맵의 크기
	static int currentRow, currentCol; // 현재 전차의 위치
	static int direction; // 전차가 바라보는 방향
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= TC; testCase++) {
			init();
			
			checkQueries();
			
			sb.append("#").append(testCase).append(" ");
			printMap();
		}
		
		
		System.out.print(sb);
	}
	
	// 명령 실행
	static void checkQueries() {
		int queryLength = queries.length();
		for (int queryIndex = 0; queryIndex < queryLength; queryIndex++) {
			char currentQuery = queries.charAt(queryIndex);
			
			// 만약 포탄을 발사하는 경우
			if (currentQuery == 'S') {
				shoot();
			}
			else {
				// 이동하는 경우
				for (int index = 0; index < 4; index++) {
					if (currentQuery == DIRECTIONS[index]) {
						// 우선 바라보는 방향과 탱크 모양 변경
						direction = index;
						map[currentRow][currentCol] = TANK_VIEW[direction];
						
						int newRow = currentRow + ADD_ROW[direction];
						int newCol = currentCol + ADD_COL[direction];
						
						// 맵 밖으로 나가는 경우 이동 X
						if (IsInvalidPosition(newRow, newCol)) {
							break;
						}
												
						// 이동할 수 없는 지역인 경우 이동 X
						if (map[newRow][newCol] == '#' || map[newRow][newCol] == '-' || map[newRow][newCol] == '*') {
							break;
						}
						
						// 탱크 이동
						map[currentRow][currentCol] = '.';
						map[newRow][newCol] = TANK_VIEW[direction];
						currentRow = newRow;
						currentCol = newCol;
						
						break;
					}
				}

			}	
		}
	}
	
	// 발사 함수
	static void shoot() {
		int newRow = currentRow + ADD_ROW[direction];
		int newCol = currentCol + ADD_COL[direction];
		
		while (true) {
			// 발사한 곳이 맵밖인 경우 종료
			if (IsInvalidPosition(newRow, newCol)) {
				break;
			}
			
			// 강철벽에 부딪힌 경우 종료
			if (map[newRow][newCol] == '#') {
				break;
			}
			
			// 벽돌벽이라면 부수고 종료
			if (map[newRow][newCol] == '*') {
				map[newRow][newCol] = '.';
				break;
			}
			
			// 다음 위치 확인
			newRow += ADD_ROW[direction];
			newCol += ADD_COL[direction];	
		}
	}
	
	// 맵 밖인지 확인하는 메서드
	static boolean IsInvalidPosition(int row, int col) {
		return row < 0 || row >= mapRowSize || col < 0 || col >= mapColSize;
	}
	
	static void printMap() {
		for (int row = 0; row < mapRowSize; row++) {
			for (int col = 0; col < mapColSize; col++) {
				sb.append(map[row][col]);
			}
			sb.append("\n");
		}
	}
	
	static void init() throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		mapRowSize = Integer.parseInt(st.nextToken());
		mapColSize = Integer.parseInt(st.nextToken());
		
		map = new char[mapRowSize][mapColSize];
		
		direction = -1;
		for (int row = 0; row < mapRowSize; row++) {
			String mapLine = br.readLine();
			for (int col = 0; col < mapColSize; col++) {
				map[row][col] = mapLine.charAt(col);
				if (direction == -1) {
					for (int index = 0; index < 4; index++) {
						if (map[row][col] == TANK_VIEW[index]) {
							direction = index;
							currentRow = row;
							currentCol = col;
							break;
						}
					}
				}
			}
		}
		
		String len = br.readLine();
		queries = br.readLine();
	}
}
