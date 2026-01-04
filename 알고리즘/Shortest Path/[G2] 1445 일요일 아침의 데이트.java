import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1445_일요일아침의데이트 {

	static BufferedReader br;
	static StringTokenizer st;

	static final int[] ADD_ROW = {-1, 0, 1, 0};
	static final int[] ADD_COL = {0, 1, 0, -1};

	static final char TRASH = 'g';
	static final char START = 'S';
	static final char FLOWER = 'F';
	static final char EMPTY = '.';
	static final char NEAR_TRASH = 'N';

	static class Node implements Comparable<Node> {
		int row, col;
		int trashCount, nearTrashCount;

		public Node(int row, int col, int trashCount, int nearTrashCount) {
			this.row = row;
			this.col = col;
			this.trashCount = trashCount;
			this.nearTrashCount = nearTrashCount;
		}

		@Override
		public int compareTo(Node o) {
			if (this.trashCount == o.trashCount) {
				return this.nearTrashCount - o.nearTrashCount;
			}
			return this.trashCount - o.trashCount;
		}
	}

	static int rowSize, colSize;
	static char[][] map;

	static int startRow, startCol;
	static int endRow, endCol;

	public static void main(String[] args) throws IOException {
		init();
		findMinTrashWay();
	}

	static void findMinTrashWay() {
		PriorityQueue<Node> toVisit = new PriorityQueue<>();
		boolean[][] visited = new boolean[rowSize][colSize];
		toVisit.add(new Node(startRow, startCol, 0, 0));
		visited[startRow][startCol] = true;

		while (!toVisit.isEmpty()) {
			Node now = toVisit.poll();

			if (now.row == endRow && now.col == endCol) {
				System.out.printf("%d %d", now.trashCount, now.nearTrashCount);
				return;
			}

			for (int deltaIndex = 0; deltaIndex < 4; deltaIndex++) {
				int newRow = now.row + ADD_ROW[deltaIndex];
				int newCol = now.col + ADD_COL[deltaIndex];

				if (newRow < 0 || newRow >= rowSize || newCol < 0 || newCol >= colSize) {
					continue;
				}

				if (visited[newRow][newCol]) {
					continue;
				}

				visited[newRow][newCol] = true;

				switch (map[newRow][newCol]) {
					case TRASH:
						toVisit.add(new Node(newRow, newCol, now.trashCount + 1, now.nearTrashCount));
						break;
					case NEAR_TRASH:
						toVisit.add(new Node(newRow, newCol, now.trashCount, now.nearTrashCount + 1));
						break;
					// EMPTY, FLOWER
					default:
						toVisit.add(new Node(newRow, newCol, now.trashCount, now.nearTrashCount));
						break;
				}
			}
		}
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());

		map = new char[rowSize][colSize];
		for (int row = 0; row < rowSize; row++) {
			String line = br.readLine();
			for (int col = 0; col < colSize; col++) {
				map[row][col] = line.charAt(col);
				if (map[row][col] == START) {
					startRow = row;
					startCol = col;
				}
				if (map[row][col] == FLOWER) {
					endRow = row;
					endCol = col;
				}
			}
		}

		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				if (map[row][col] == TRASH) {
					for (int deltaIndex = 0; deltaIndex < 4; deltaIndex++) {
						int newRow = row + ADD_ROW[deltaIndex];
						int newCol = col + ADD_COL[deltaIndex];

						if (newRow < 0 || newRow >= rowSize || newCol < 0 || newCol >= colSize) {
							continue;
						}

						if (map[newRow][newCol] == EMPTY) {
							map[newRow][newCol] = NEAR_TRASH;
						}
					}
				}
			}
		}
	}
}
