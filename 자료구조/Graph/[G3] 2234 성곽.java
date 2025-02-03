import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int M, N;
	static int[][] map, group;
	static boolean[][] visited;
	static final int[] addRow = {0, -1, 0, 1};
	static final int[] addCol = {-1, 0, 1, 0};
	static int[] groupSize;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		group = new int[N][M];
		visited = new boolean[N][M];
		groupSize = new int[N * M + 1];
		
		for (int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < M; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}

		int groupCount = 0;
		int maxSingleGroupSize = 0;
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < M; col++) {
				if (!visited[row][col]) {
					int size = bfs(row, col, ++groupCount);
					groupSize[groupCount] = size;
					maxSingleGroupSize = Math.max(maxSingleGroupSize, size);
				}
			}
		}
		
		int maxDuoGroupSize = maxSingleGroupSize;
		maxDuoGroupSize = findMaxDuoGroupSize();
		
		System.out.printf("%d\n%d\n%d\n", groupCount, maxSingleGroupSize, maxDuoGroupSize);
	}
	
	static int bfs(int row, int col, int groupNum) {
		Queue<int[]> toVisit = new LinkedList<>();
		toVisit.add(new int[] {row, col});
		visited[row][col] = true;
		
		int size = 0;
		while (!toVisit.isEmpty()) {
			int[] now = toVisit.poll();
			int nowRow = now[0];
			int nowCol = now[1];
			
			size++;
			group[nowRow][nowCol] = groupNum;
			
			for (int k = 0; k < 4; k++) {
				if ((map[nowRow][nowCol] & (1 << k)) != 0) {
					continue;
				}
				
				int nextRow = nowRow + addRow[k];
				int nextCol = nowCol + addCol[k];
				
				if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) {
					continue;
				}
				
				if (visited[nextRow][nextCol]) {
					continue;
				}
				visited[nextRow][nextCol] = true;
				
				toVisit.add(new int[] {nextRow, nextCol});
			}
		}
		
		return size;
	}
	
	static int findMaxDuoGroupSize() {
		int max = 0;
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < M - 1; col++) {
				if (group[row][col] != group[row][col + 1]) {
					max = Math.max(max, groupSize[group[row][col]] + groupSize[group[row][col + 1]]);
				}
			}
		}
		for (int col = 0; col < M; col++) {
			for (int row = 0; row < N - 1; row++) {
				if (group[row][col] != group[row + 1][col]) {
					max = Math.max(max, groupSize[group[row][col]] + groupSize[group[row + 1][col]]);
				}
			}
		}
		
		return max;
	}

}
