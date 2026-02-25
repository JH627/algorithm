import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_17836_공주님을구해라 {

	static BufferedReader br;
	static StringTokenizer st;

	static final int[] ADD_ROW = {-1, 0, 1, 0};
	static final int[] ADD_COL = {0, 1, 0, -1};
	static final int EMPTY = 0;
	static final int WALL = 1;
	static final int GRAM = 2;

	static class Node implements Comparable<Node> {
		int row, col;
		int distance;
		int gram;

		public Node(int row, int col, int distance, int gram) {
			this.row = row;
			this.col = col;
			this.distance = distance;
			this.gram = gram;
		}

		@Override
		public int compareTo(Node o) {
			return this.distance - o.distance;
		}
	}

	static int rowSize, colSize, timeLimit;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		init();
		int minTime = getMinTime();
		System.out.print(minTime == -1 ? "Fail" : minTime);
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		timeLimit = Integer.parseInt(st.nextToken());

		map = new int[rowSize][colSize];
		for (int row = 0; row < rowSize; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < colSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
			}
		}
	}

	static int getMinTime() {
		boolean[][][] visited = new boolean[rowSize][colSize][2];
		PriorityQueue<Node> toVisit = new PriorityQueue<>();

		visited[0][0][0] = true;
		toVisit.add(new Node(0, 0, 0, 0));

		while (!toVisit.isEmpty()) {
			Node now = toVisit.poll();

			if (now.row == rowSize - 1 && now.col == colSize - 1) {
				return now.distance;
			}

			if (now.distance == timeLimit) {
				continue;
			}

			for (int deltaIndex = 0; deltaIndex < 4; deltaIndex++) {
				int newRow = now.row + ADD_ROW[deltaIndex];
				int newCol = now.col + ADD_COL[deltaIndex];

				if (newRow < 0 || newRow >= rowSize || newCol < 0 || newCol >= colSize) {
					continue;
				}

				if (visited[newRow][newCol][now.gram]) {
					continue;
				}

				if (now.gram == 1) {
					visited[newRow][newCol][1] = true;
					toVisit.add(new Node(newRow, newCol, now.distance + 1, 1));
				}
				else if (map[newRow][newCol] == EMPTY){
					visited[newRow][newCol][0] = true;
					toVisit.add(new Node(newRow, newCol, now.distance + 1, 0));
				}
				else if (map[newRow][newCol] == GRAM) {
					visited[newRow][newCol][0] = true;
					visited[newRow][newCol][1] = true;
					toVisit.add(new Node(newRow, newCol, now.distance + 1, 1));
				}
			}
		}

		return -1;
	}
}
