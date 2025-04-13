import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 다음에 물이 도착하는 곳에는 고슴도치가 갈 수 없음
 * => 물부터 BFS 탐색
 * 
 * 1. 입력을 받고 Queue에 물 위치, 고슴도치 위치 순으로 넣는다
 * 2. Queue에서 하나씩 꺼내서 확인한다
 *	2-1. 만약 고슴도치인 경우
 *		2-1-1. 다음이 탈출지역인 경우 현재까지의 거리 + 1을 출력하고 종료
 *		2-1-2. 만약 이미 이전에 방문한 곳이거나 물, 벽이 있는 경우는 가지 않음
 *		2-1-3. 갈 수 있는 곳이라면 queue에 넣는다.
 * 	2-2. 만약 물인 경우
 * 		2-2-1. 만약 이미 물이 있거나 벽이 있는 경우는 더이상 퍼지지않는다
 *		2-2-2. 탈출구가 있는 경우에도 가지않는다
 *
 */
public class BOJ_3055_탈출 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static final int[] ADD_ROW = {-1, 0, 1, 0};
	static final int[] ADD_COL = {0, 1, 0, -1};
	
	static class Point {
		int row, col;
		int type, distance;
		
		public Point(int row, int col, int type, int distance) {
			this.row = row;
			this.col = col;
			this.type = type;
			this.distance = distance;
		}
	}
	
	static int rowSize, colSize;
	static char[][] map;
	static boolean[][] visited, impossible;
	static Point startPoint;
	static ArrayList<Point> waterPoints;
	
	public static void main(String[] args) throws Exception {
		init();
		
		int time = move();
		
		System.out.println(time == -1 ? "KAKTUS" : time);
	}
	
	static int move() {
		Queue<Point> toVisit = new LinkedList<>();
		
		// 물이 먼저 찰 예정
		for (Point water : waterPoints) {
			toVisit.add(water);
		}
		// 그 다음이 고슴도치
		toVisit.add(startPoint);
		
		// 고슴도치 지나간 자리
		visited[startPoint.row][startPoint.col] = true;
		while (!toVisit.isEmpty()) {
			Point now = toVisit.poll();
			
			for (int index = 0; index < 4; index++) {
				int newRow = now.row + ADD_ROW[index];
				int newCol = now.col + ADD_COL[index];
				
				if (newRow < 0 || newRow >= rowSize || newCol < 0 || newCol >= colSize) {
					continue;
				}
				
				if (impossible[newRow][newCol]) {
					continue;
				}
				
				// 고슴도치인 경우
				if (now.type == 0) {
					// 탈출 가능한 경우
					if (map[newRow][newCol] == 'D') {
						return now.distance + 1;
					}
					else {
						// 이전에 방문한 경우
						if (visited[newRow][newCol]) {
							continue;
						}
						visited[newRow][newCol] = true;
						toVisit.add(new Point(newRow, newCol, 0, now.distance + 1));
					}
				}
				// 물인 경우
				else {
					// 탈출구에는 물이 들어가지 않음
					if (map[newRow][newCol] == 'D') {
						continue;
					}
					impossible[newRow][newCol] = true;
					toVisit.add(new Point(newRow, newCol, 1, 0));
				}
			}
		}
		
		return -1;
	}

	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		
		map = new char[rowSize][colSize];
		visited = new boolean[rowSize][colSize];
		impossible = new boolean[rowSize][colSize];
		waterPoints = new ArrayList<>();
		for (int row = 0; row < rowSize; row++) {
			String line = br.readLine();
			for (int col = 0; col < colSize; col++) {
				map[row][col] = line.charAt(col);
				// 물
				if (map[row][col] == '*') {
					impossible[row][col] = true;
					waterPoints.add(new Point(row, col, 1, 0));
				}
				// 돌
				else if (map[row][col] == 'X') {
					impossible[row][col] = true;
				}
				// 고슴도치
				else if (map[row][col] == 'S') {
					startPoint = new Point(row, col, 0, 0);
				}
			}
		}
	}
}
