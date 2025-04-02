import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1. 말을 이동시킨다.
 * 1-1. 만약 점프 가능 횟수가 남아있는 경우 (이동 시 점프 횟수 - 1)
 * 1-1-1. 만약 기둥이 있는 경우 이동하지 않음
 * 1-1-2. 만약 새로 이동하려는 곳에 더 많은 점프횟수를 가지고 이동할 수 있는 경우 이동
 * 1-1-3. 만약 방문한 곳인 경우 이동하지 않음
 * 1-1-4. 방문 안한 곳인 경우 이동
 * 
 * 2-2. 점프 가능 횟수가 안 남아있는 경우 (이동 시 점프 횟수 그대로)
 * 2-1-1. 만약 기둥이 있는 경우 이동하지 않음
 * 2-1-2. 만약 새로 이동하려는 곳에 더 많은 점프횟수를 가지고 이동할 수 있는 경우 이동
 * 2-1-3. 만약 방문한 곳인 경우 이동하지 않음
 * 2-1-4. 방문 안한 곳인 경우 이동
 *
 * 3. 오른쪽 맨 아래에 도착한 경우 종료
 */
public class BOJ_1600_말이되고픈원숭이 {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static final int[] ADD_ROW = {-1, 0, 1, 0};
	static final int[] ADD_COL = {0, -1, 0, 1};
	
	static final int[] ADD_JUMP_ROW = {-1, -2, -2, -1, 1, 2, 2, 1};
	static final int[] ADD_JUMP_COL = {-2, -1, 1, 2, 2, 1, -1, -2};
	
	static class Node implements Comparable<Node> {
		int row, col, distance, jumpCount;
		
		public Node(int row, int col, int distance, int jumpCount) {
			this.row = row;
			this.col = col;
			this.distance = distance;
			this.jumpCount = jumpCount;
		}
		
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.distance, o.distance);
		}
	}

	static boolean[][] map, visited;
	static int[][] remainJumpCount;
	static int rowSize, colSize;
	static int jumpLimit;
	
	public static void main(String[] args) throws Exception {
		init();
		
		int minDistance = findMinDistance();
		
		System.out.print(minDistance);
	}
	
	static int findMinDistance() {
		visited = new boolean[rowSize][colSize];
		remainJumpCount = new int[rowSize][colSize];
		
		Queue<Node> toVisit = new LinkedList<>();
		toVisit.add(new Node(0, 0, 0, jumpLimit));
		visited[0][0] = true;
		remainJumpCount[0][0] = jumpLimit;
		
		while (!toVisit.isEmpty()) {
			Node now = toVisit.poll();
			
			// 도착
			if (now.row == rowSize - 1 && now.col == colSize - 1) {
				return now.distance;
			}
			
			// 점프 횟수가 남아있는 경우
			if (now.jumpCount > 0) {
				for (int deltaIndex = 0; deltaIndex < 8; deltaIndex++) {
					int newRow = now.row + ADD_JUMP_ROW[deltaIndex];
					int newCol = now.col + ADD_JUMP_COL[deltaIndex];
					
					if (newRow < 0 || newRow >= rowSize || newCol < 0 || newCol >= colSize) {
						continue;
					}

					// 벽은 못감
					if (map[newRow][newCol]) {
						continue;
					}
					
					// 더 나은 점프 횟수를 가지고 이동할 수 있는 경우
					if (remainJumpCount[newRow][newCol] < now.jumpCount - 1) {
						remainJumpCount[newRow][newCol] = now.jumpCount - 1;
						visited[newRow][newCol] = true;
						toVisit.add(new Node(newRow, newCol, now.distance + 1, now.jumpCount - 1));
						continue;
					}
					
					if (visited[newRow][newCol]) {
						continue;
					}
					
					// 방문 안 한 경우
					visited[newRow][newCol] = true;
					toVisit.add(new Node(newRow, newCol, now.distance + 1, now.jumpCount - 1));
				}
			}
			
			// 점프 횟수를 다 쓴 경우
			for (int deltaIndex = 0; deltaIndex < 4; deltaIndex++) {
				int newRow = now.row + ADD_ROW[deltaIndex];
				int newCol = now.col + ADD_COL[deltaIndex];
				
				if (newRow < 0 || newRow >= rowSize || newCol < 0 || newCol >= colSize) {
					continue;
				}
				// 벽은 못감
				if (map[newRow][newCol]) {
					continue;
				}
				
				// 더 나음 점프 횟수를 가지고 이동할 수 있는 경우
				if (remainJumpCount[newRow][newCol] < now.jumpCount) {
					remainJumpCount[newRow][newCol] = now.jumpCount;
					visited[newRow][newCol] = true;
					toVisit.add(new Node(newRow, newCol, now.distance + 1, now.jumpCount));
					continue;
				}
				
				if (visited[newRow][newCol]) {
					continue;
				}
				
				// 방문 안한 경우
				visited[newRow][newCol] = true;
				toVisit.add(new Node(newRow, newCol, now.distance + 1, now.jumpCount));
			}
		}

		return -1;
	}
	
	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		jumpLimit = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		colSize = Integer.parseInt(st.nextToken());
		rowSize = Integer.parseInt(st.nextToken());

		map = new boolean[rowSize][colSize];
		for (int row = 0; row < rowSize; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < colSize; col++) {
				if (Integer.parseInt(st.nextToken()) == 1) {
					map[row][col] = true;
				}
			}
		}
		
	}
}
