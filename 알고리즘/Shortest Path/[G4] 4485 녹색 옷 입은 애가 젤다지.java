import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static final int[] ADD_ROW = {-1, 0, 1, 0};
	static final int[] ADD_COL = {0, -1, 0, 1};

	static int N;
	static int[][] map;

	static class Node implements Comparable<Node> {
		int row, col;
		int dist;

		public Node(int row, int col, int dist) {
			this.row = row;
			this.col = col;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node o) {
			return this.dist - o.dist;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		int testcase = 0;
		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0) {
				break;
			}
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			sb.append("Problem ").append(++testcase).append(": ").append(dijkstra()).append("\n");
		}

		System.out.print(sb);
	}

	static int dijkstra() {
		boolean[][] visited = new boolean[N][N];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(0, 0, map[0][0]));

		while (!pq.isEmpty()) {
			Node now = pq.poll();

			if (visited[now.row][now.col]) {
				continue;
			}
			visited[now.row][now.col] = true;

			if (now.row == N - 1 && now.col == N - 1) {
				return now.dist;
			}

			for (int k = 0; k < 4; k++) {
				int newRow = now.row + ADD_ROW[k];
				int newCol = now.col + ADD_COL[k];

				if (newRow < 0 || newRow >= N || newCol < 0 || newCol >= N) {
					continue;
				}
				if (visited[newRow][newCol]) {
					continue;
				}

				pq.add(new Node(newRow, newCol, now.dist + map[newRow][newCol]));
			}
		}
		return 0;
	}
}
