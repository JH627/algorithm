import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1944_복제로봇 {

	static BufferedReader br;
	static StringTokenizer st;

	static final char WALL = '1';
	static final char EMPTY = '0';
	static final char START = 'S';
	static final char KEY = 'K';

	static final int[] ADD_ROW = {-1, 0, 1, 0};
	static final int[] ADD_COL = {0, 1, 0, -1};

	static class Edge implements Comparable<Edge> {
		int a, b;
		int distance;

		public Edge(int a, int b, int distance) {
			this.a = a;
			this.b = b;
			this.distance = distance;
		}

		@Override
		public int compareTo(Edge o) {
			return this.distance - o.distance;
		}
	}

	static int mapSize, keyCount;
	static char[][] map;
	static int[][] keyNumberMap;
	static PriorityQueue<Edge> edges;

	static int[] parent, rank;

	public static void main(String[] args) throws IOException {
		init();
		getEdges();
		System.out.print(findMinDistance());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		mapSize = Integer.parseInt(st.nextToken());
		keyCount = Integer.parseInt(st.nextToken());

		map = new char[mapSize][mapSize];
		keyNumberMap = new int[mapSize][mapSize];
		int keyIndex = 0;
		for (int row = 0; row < mapSize; row++) {
			String line = br.readLine();
			for (int col = 0; col < mapSize; col++) {
				map[row][col] = line.charAt(col);
				if (map[row][col] == KEY || map[row][col] == START) {
					keyNumberMap[row][col] = ++keyIndex;
				}
			}
		}

		parent = new int[keyCount + 2];
		rank = new int[keyCount + 2];
		for (int key = 0; key < keyCount + 2; key++) {
			parent[key] = key;
		}
	}

	static void getEdges() {
		edges = new PriorityQueue<>();

		for (int row = 0; row < mapSize; row++) {
			for (int col = 0; col < mapSize; col++) {
				if (keyNumberMap[row][col] > 0) {
					findDistance(row, col);
				}
			}
		}
	}

	static void findDistance(int startRow, int startCol) {
		boolean[][] visited = new boolean[mapSize][mapSize];
		Queue<int[]> toVisit = new LinkedList<>();

		visited[startRow][startCol] = true;
		toVisit.add(new int[]{startRow, startCol, 0});
		int checkedKeyCount = 1;
		while (!toVisit.isEmpty() && checkedKeyCount < keyCount + 1) {
			int[] now = toVisit.poll();
			int nowRow = now[0];
			int nowCol = now[1];
			int nowDistance = now[2];

			for (int deltaIndex = 0; deltaIndex < 4; deltaIndex++) {
				int newRow = nowRow + ADD_ROW[deltaIndex];
				int newCol = nowCol + ADD_COL[deltaIndex];

				if (newRow < 0 || newRow >= mapSize || newCol < 0 || newCol >= mapSize) {
					continue;
				}

				if (visited[newRow][newCol] || map[newRow][newCol] == WALL) {
					continue;
				}
				
				if (keyNumberMap[newRow][newCol] > keyNumberMap[startRow][startCol]) {
					edges.add(new Edge(keyNumberMap[startRow][startCol], keyNumberMap[newRow][newCol], nowDistance + 1));
					edges.add(new Edge(keyNumberMap[newRow][newCol], keyNumberMap[startRow][startCol], nowDistance + 1));
					checkedKeyCount++;
				}

				visited[newRow][newCol] = true;
				toVisit.add(new int[]{newRow, newCol, nowDistance + 1});
			}
		}
	}

	static int findMinDistance() {
		int selectedEdgeCount = 0;
		int distanceSum = 0;

		while (!edges.isEmpty()) {
			Edge edge = edges.poll();

			if (union(edge.a, edge.b)) {
				distanceSum += edge.distance;

				if (++selectedEdgeCount == keyCount) {
					return distanceSum;
				}
			}
		}

		return -1;
	}

	static int find(int a) {
		if (parent[a] == a) {
			return a;
		}
		return parent[a] = find(parent[a]);
	}

	static boolean union(int a, int b) {
		int pa = find(a);
		int pb = find(b);

		if (pa == pb) {
			return false;
		}

		if (rank[pa] < rank[pb]) {
			parent[pa] = pb;
		}
		else if (rank[pa] > rank[pb]) {
			parent[pb] = pa;
		}
		else {
			parent[pa] = pb;
			rank[pa]++;
		}

		return true;
	}
}
