import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1. 맵에 벽을 3개 설치한다.
 * 1-1. depth가 3이라면 BFS 탐색을 실시한다.
 * 1-2. 0인 곳에 벽을 설치하고 depth를 1늘린다.
 * 
 * 2. 바이러스의 초기 위치를 시작지점으로 BFS 탐색을 하며 안전영역의 개수를 확인한다.
 * 2-1. 0인 곳으로 바이러스가 퍼짐
 * 2-2. 만약 현재 최대 안전구역보다 탐색중에 구역이 작아지면 탐색 종료
 *
 */
public class BOJ_14502_연구소 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static final int[] ADD_ROW = {-1, 0, 1, 0};
	static final int[] ADD_COL = {0, 1, 0, -1};
	
	static class Point {
		int row, col;

		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
		
	}
	
	static int rowSize, colSize, emptySize;
	static int[][] map;
	static ArrayList<Point> virusPoints; // 바이러스 시작 지점
	
	static int maxSize = 0;
	
	public static void main(String[] args) throws Exception {
		init();
		
		setWall(0, 0);
		
		System.out.print(maxSize);
	}
	
	static void setWall(int pos, int depth) {
		// 벽을 3개 다 설치한 경우
		if (depth == 3) {
			maxSize = Math.max(maxSize, calcArea());
			return;
		}
		
		// 벽을 추가로 더 설치해야하는 경우
		for (int next = pos; next < rowSize * colSize - (2 - depth); next++) {
			if (map[next / colSize][next % colSize] == 0) {
				// 벽 설치
				map[next / colSize][next % colSize] = 1;
				emptySize--;
				
				setWall(next + 1, depth + 1);
				
				// 벽 부수기
				map[next / colSize][next % colSize] = 0;
				emptySize++;
			}
		}
	}
	
	static int calcArea() {
		// 현재 안전구역의 넓이
		int size = emptySize + virusPoints.size();
		
		Queue<Point> toVisit = new LinkedList<>();
		for (Point point : virusPoints) {
			toVisit.add(point);
		}
		
		boolean[][] visited = new boolean[rowSize][colSize];
		while (!toVisit.isEmpty()) {
			Point now = toVisit.poll();
			
			if (--size <= maxSize) {
				return maxSize;
			}
			
			for (int index = 0; index < 4; index++) {
				int newRow = now.row + ADD_ROW[index];
				int newCol = now.col + ADD_COL[index];
				
				if (newRow < 0 || newRow >= rowSize || newCol < 0 || newCol >= colSize) {
					continue;
				}
				if (visited[newRow][newCol]) {
					continue;
				}
				
				// 바이러스가 아직 퍼지지 않은 곳인경우
				if (map[newRow][newCol] == 0) {
					visited[newRow][newCol] = true;
					toVisit.add(new Point(newRow, newCol));
				}
			}
		}
		
		return size;
	}
	
	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		
		virusPoints = new ArrayList<>();
		emptySize = rowSize * colSize;
		map = new int[rowSize][colSize];
		for (int row = 0; row < rowSize; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < colSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
				if (map[row][col] == 1) {
					emptySize--;
				}
				else if (map[row][col] == 2) {
					emptySize--;
					virusPoints.add(new Point(row, col));
				}		
			}
		}
		
		maxSize = 0;
	}
}
