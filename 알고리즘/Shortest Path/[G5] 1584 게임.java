import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1584_게임 {

	static BufferedReader br;
	static StringTokenizer st;

	static final int MAP_SIZE = 500;
	static final int NORMAL = 0;
	static final int WARN = 1;
	static final int DEATH = 2;

	static final int[] ADD_ROW = {-1, 0, 1, 0};
	static final int[] ADD_COL = {0, -1, 0, 1};

	static class Node implements Comparable<Node> {
		int row, col;
		int damage;

		public Node(int row, int col, int damage) {
			this.row = row;
			this.col = col;
			this.damage = damage;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.damage, o.damage);
		}
	}

	static int[][] map;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getMaxHealth());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		map = new int[MAP_SIZE + 1][MAP_SIZE + 1];
		int warnAreaCount = Integer.parseInt(br.readLine());
		for (int area = 0; area < warnAreaCount; area++) {
			st = new StringTokenizer(br.readLine());

			int startCol = Integer.parseInt(st.nextToken());
			int startRow = Integer.parseInt(st.nextToken());
			int endCol = Integer.parseInt(st.nextToken());
			int endRow = Integer.parseInt(st.nextToken());

			for (int row = Math.min(startRow, endRow); row <= Math.max(startRow, endRow); row++) {
				for (int col = Math.min(startCol, endCol); col <= Math.max(startCol, endCol); col++) {
					map[row][col] = WARN;
				}
			}
		}

		int deathAreaCount = Integer.parseInt(br.readLine());
		for (int area = 0; area < deathAreaCount; area++) {
			st = new StringTokenizer(br.readLine());

			int startCol = Integer.parseInt(st.nextToken());
			int startRow = Integer.parseInt(st.nextToken());
			int endCol = Integer.parseInt(st.nextToken());
			int endRow = Integer.parseInt(st.nextToken());

			for (int row = Math.min(startRow, endRow); row <= Math.max(startRow, endRow); row++) {
				for (int col = Math.min(startCol, endCol); col <= Math.max(startCol, endCol); col++) {
					map[row][col] = DEATH;
				}
			}
		}
	}

	static int getMaxHealth() {
		boolean[][] visited = new boolean[MAP_SIZE + 1][MAP_SIZE + 1];
		PriorityQueue<Node> toVisit = new PriorityQueue<>();

		toVisit.add(new Node(0, 0, 0));
		while (!toVisit.isEmpty()) {
			Node now = toVisit.poll();

			if (visited[now.row][now.col]) {
				continue;
			}
			visited[now.row][now.col] = true;

			if (now.row == MAP_SIZE && now.col == MAP_SIZE) {
				return now.damage;
			}

			for (int deltaIndex = 0; deltaIndex < 4; deltaIndex++) {
				int newRow = now.row + ADD_ROW[deltaIndex];
				int newCol = now.col + ADD_COL[deltaIndex];

				if (newRow < 0 || newRow >= MAP_SIZE + 1 || newCol < 0 || newCol >= MAP_SIZE + 1) {
					continue;
				}

				if (visited[newRow][newCol]) {
					continue;
				}

				switch (map[newRow][newCol]) {
					case DEATH:
						break;
					case WARN:
						toVisit.add(new Node(newRow, newCol, now.damage + 1));
						break;
					case NORMAL:
						toVisit.add(new Node(newRow, newCol, now.damage));
						break;
				}
			}
		}

		return -1;
	}
}
