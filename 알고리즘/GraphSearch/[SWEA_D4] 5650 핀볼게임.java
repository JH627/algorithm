import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 0인 지점에서 4방향으로 출발시킨다
 * 	1-1. 만약 다음 지점이
 * 		1-1-1. 벽인 경우
 *			1-1-1-1. 점수를 1 올리고 방향을 반대로 뒤집음
 *		1-2-1. 블랙홀인 경우
 *			1-2-1-1. 탐색을 종료하고 현재 점수와 최대 점수 비교
 *		1-3-1. 빈 공간인 경우
 *			1-3-1-1. 계속 진행
 *		1-4-1. 일반 블럭인 경우
 *			1-4-1-1. 블럭 종류에 따라 반사되는 방향을 확인
 *			1-4-1-2. 점수를 1점 올리고 계속 진행
 *		1-5-1. 웜홀인 경우
 *			1-5-1-1. 반대편 웜홀 좌표로 이동시킨다.
 * 
 * 2. 최대 점수를 출력
 *
 */
public class SWEA_5650_핀볼게임 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	// 상, 우, 하, 좌
	static final int[] ADD_ROW = {-1, 0, 1, 0};
	static final int[] ADD_COL = {0, 1, 0, -1};

	// 블럭 굴절되는 방향
	static final int[][] BLOCK_REFRACTION = {
			// 0번 블럭 (초기화 용)
			{-1, -1, -1, -1}, 
			// 1번 블럭 (하 -> 우, 좌 -> 상)
			{2, 3, 1, 0},
			// 2번 블럭 (상 -> 우, 좌 -> 하)
			{1, 3, 0, 2},
			// 3번 블럭 (상 -> 좌, 우 -> 하)
			{3, 2, 0, 1},
			// 4번 블럭 (우 -> 상, 하 -> 좌)
			{2, 0, 3, 1},
			// 5번 블럭 (반사 X)
			{2, 3, 0, 1}
	};
	
	static class Point {
		int row, col;

		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + col;
			result = prime * result + row;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Point other = (Point) obj;
			if (col != other.col)
				return false;
			if (row != other.row)
				return false;
			return true;
		}
	}
	
	static int mapSize;

	static Point[][] holes;
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= TC; testCase++) {
			init();
			
			int maxScore = 0;
			for (int row = 0; row < mapSize; row++) {
				for (int col = 0; col < mapSize; col++) {
					if (map[row][col] == 0) {
						maxScore = Math.max(maxScore, getMaxScore(row, col));
					}
				}
			}
			
			sb.append("#").append(testCase).append(" ").append(maxScore).append("\n");
		}
		
		System.out.print(sb);
	}
	
	static int getMaxScore(int row, int col) {
		int maxScore = 0;
		
		int newRow, newCol;
		for (int direction = 0; direction < 4; direction++) {
			newRow = row;
			newCol = col;
			
			int score = 0;
			int dir = direction;
			while (true) {
				newRow += ADD_ROW[dir];
				newCol += ADD_COL[dir];
				
				// 벽인 경우
				if (newRow < 0 || newRow >= mapSize || newCol < 0 || newCol >= mapSize) {
					score++;
					// 방향 뒤집기
					dir = (dir + 2) % 4;
					continue;
				}
				
				int type = map[newRow][newCol];
				
				// 블랙홀인 경우
				if (type == -1 || (newRow == row && newCol == col)) {
					break;
				}
				
				// 빈 공간인 경우 계속 진행
				if (type == 0) {
					continue;
				}
				
				// 일반 블럭인 경우
				if (type <= 5) {
					dir = BLOCK_REFRACTION[type][dir];
					score++;
				}
				
				// 웜홀인 경우
				if (type >= 6) {
					if (holes[type][0].equals(new Point(newRow, newCol))) {
						newRow = holes[type][1].row;
						newCol = holes[type][1].col;
					}
					else {
						newRow = holes[type][0].row;
						newCol = holes[type][0].col;
					}
				}
			}
			
			maxScore = Math.max(maxScore, score);
		}
		
		return maxScore;
	}
	
	static void init() throws Exception {
		mapSize = Integer.parseInt(br.readLine().trim());
		
		holes = new Point[11][2];
		map = new int[mapSize][mapSize];
		for (int row = 0; row < mapSize; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 0; col < mapSize; col++) {
				int num = Integer.parseInt(st.nextToken());
				map[row][col] = num;
				// 웜홀
				if (num >= 6) {
					if (holes[num][0] != null) {
						holes[num][1] = new Point(row, col);
					}
					else {
						holes[num][0] = new Point(row, col);
					}
				}
			}
		}
	}
}
