import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, L, R;
	static int[][] map;
	static boolean[][] visited;
	static final int[] dr = {-1, 1, 0, 0};
	static final int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		for (int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < N; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		int time = 0;
		while (true) {
			boolean updated = false;
			visited = new boolean[N][N];
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < N; col++) {
					if (!visited[row][col]) {
						if (updateArea(row, col)) {
							updated = true;
						}
					}
				}
			}
			
			if (!updated) {
				break;
			}
			time++;
		}
		
		System.out.print(time);
	}
	
	
	static boolean updateArea(int row, int col) {
		Queue<int[]> toVisit = new LinkedList<>();
		
		int sum = 0;
		ArrayList<int[]> visitedArea = new ArrayList<>();
		
		toVisit.add(new int[] {row, col});
		visited[row][col] = true;
		while (!toVisit.isEmpty()) {
			int[] now = toVisit.poll();
			
			sum += map[now[0]][now[1]];
			visitedArea.add(new int[] {now[0], now[1]});
		
			for (int index = 0; index < 4; index++) {
				int newRow = now[0] + dr[index];
				int newCol = now[1] + dc[index];
				
				if (newRow < 0 || newRow >= N || newCol < 0 || newCol >= N) {
					continue;
				}
				
				if (visited[newRow][newCol]) {
					continue;
				}
				
				int difference = Math.abs(map[newRow][newCol] - map[now[0]][now[1]]);
				
				if (difference < L || difference > R) {
					continue;
				}
				
				visited[newRow][newCol] = true;
				toVisit.add(new int[] {newRow, newCol});
			}
		}
		
		sum = sum / visitedArea.size();
		
		for (int[] area : visitedArea) {
			map[area[0]][area[1]] = sum;
		}
		
		return visitedArea.size() != 1;
	}

}
