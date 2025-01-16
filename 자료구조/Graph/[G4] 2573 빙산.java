import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[][] map;
	static int area, startX, startY;
	static final int[] dx = {0, 0, 1, -1};
	static final int[] dy = {-1, 1, 0, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		area = 0;
		startX = -1;
		startY = -1;
		map = new int[N][M];
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int m = 0; m < M; m++) {
				map[n][m] = Integer.parseInt(st.nextToken());
				if (map[n][m] > 0) {
					area++;
					if (startX == -1) {
						startX = n;
						startY = m;
					}
				}
			}
		}
		
		int year = 0;
		while (true) {
			if (map[startX][startY] <= 0) {
				System.out.print(0);
				return;
			}
			if (!bfs()) {
				break;
			}
			year++;
		}
		
		System.out.print(year);
	}
	
	static boolean bfs() {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		q.add(new int[] {startX, startY});
		
		int visit = 0;
		int removed = 0;
		while (!q.isEmpty()) {
			int[] now = q.poll();
			int now_x = now[0];
			int now_y = now[1];
			
			if (visited[now_x][now_y]) {
				continue;
			}
			visit++;
			visited[now_x][now_y] = true;
			for (int i = 0; i < 4; i++) {
				int new_x = now_x + dx[i];
				int new_y = now_y + dy[i];

				if (new_x < 0 || new_x >= N || new_y < 0 || new_y >= M) {
					continue;
				}
				if (visited[new_x][new_y]) {
					continue;
				}
				if (map[new_x][new_y] <= 0) {
					map[now_x][now_y]--;
				}
				else {
					q.add(new int[] {new_x, new_y});
				}
			}
			
			if (map[now_x][now_y] <= 0) {
				removed++;
			}
			else {
				startX = now_x;
				startY = now_y;
			}
			
		}
		
		if (visit == area) {
			area -= removed;
			return true;
		}
		return false;
	}
	
	

}
