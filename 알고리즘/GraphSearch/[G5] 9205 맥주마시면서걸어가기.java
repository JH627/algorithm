import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1. 시작 지점에서 출발
 * 	1-1. 다른 노드들까지의 거리 측정
 * 		1-1-1. 만약 맥주를 마시면서 갈수 있다면 (거리 <= 20 * 50)
 * 		1-1-2. 다음 노드를 queue에 추가
 * 		1-1-3. 만약 도착 노드(페스티벌)이라면 true반환
 * 	
 * 2. 모든 노드를 다 확인 했는데 도착지점에 도달하지 못했다면 false 반환
 *
 */
public class BOJ_9205_맥주마시면서걸어가기 {
  
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static final int LIMIT = 20 * 50;
	
	static class Point {
		int row, col;

		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	static int storeCount;
	static ArrayList<Point> points;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 0; testCase < TC; testCase++) {
			init();
			
			boolean possible = simulate();
			
			sb.append(possible ? "happy\n" : "sad\n");
		}
		
		System.out.println(sb);
	}

	static boolean simulate() {
		boolean[] visited = new boolean[storeCount + 2];
		Queue<Integer> toVisit = new LinkedList<>();
		
		toVisit.add(0);
		visited[0] = true;
		
		while (!toVisit.isEmpty()) {
			int now = toVisit.poll();
			
			Point nowPoint = points.get(now);
			for (int next = 0; next < points.size(); next++) {
				if (visited[next]) {
					continue;
				}
				
				Point nextPoint = points.get(next);
				int distance = getDistance(nowPoint.row, nowPoint.col, nextPoint.row, nextPoint.col);
				
				if (distance > LIMIT) {
					continue;
				}
				
				if (next == storeCount + 1) {
					return true;
				}
				
				visited[next] = true;
				toVisit.add(next);
			}
		}
		
		return false;
	}
	
	static int getDistance(int startRow, int startCol , int endRow, int endCol) {
		return Math.abs(startRow - endRow) + Math.abs(startCol - endCol);
	}
	
	static void init() throws Exception {
		storeCount = Integer.parseInt(br.readLine());
		
		points = new ArrayList<>();
		for (int pointIndex = 0; pointIndex < storeCount + 2; pointIndex++) {
			st = new StringTokenizer(br.readLine());
			
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			
			points.add(new Point(row, col));
		}
	}
}
