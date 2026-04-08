import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_18769_그리드네트워크 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static class Edge implements Comparable<Edge> {
		int a, b;
		int cost;

		public Edge(int a, int b, int cost) {
			this.a = a;
			this.b = b;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.cost, o.cost);
		}
	}

	static int rowSize, colSize;
	static PriorityQueue<Edge> edges;

	static int[] parent, rank;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 0; testCase < TC; testCase++) {
			init();
			sb.append(findMinCost()).append("\n");
		}

		System.out.print(sb);
	}

	static void init() throws IOException {
		st = new StringTokenizer(br.readLine());

		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());

		parent = new int[rowSize * colSize];
		rank = new int[rowSize * colSize];
		for (int node = 0; node < rowSize * colSize; node++) {
			parent[node] = node;
		}

		edges = new PriorityQueue<>();
		for (int rowIndex = 0; rowIndex < rowSize; rowIndex++) {
			st = new StringTokenizer(br.readLine());
			for (int colIndex = 0; colIndex < colSize - 1; colIndex++) {
				int a = rowIndex * colSize + colIndex;
				int b = rowIndex * colSize + colIndex + 1;

				int cost = Integer.parseInt(st.nextToken());
				edges.add(new Edge(a, b, cost));
			}
		}

		for (int rowIndex = 0; rowIndex < rowSize - 1; rowIndex++) {
			st = new StringTokenizer(br.readLine());
			for (int colIndex = 0; colIndex < colSize; colIndex++) {
				int a = rowIndex * colSize + colIndex;
				int b = (rowIndex + 1) * colSize + colIndex;

				int cost = Integer.parseInt(st.nextToken());
				edges.add(new Edge(a, b, cost));
			}
		}
	}

	static int findMinCost() {
		int costSum = 0;
		int connectedEdgeCount = 0;

		while (!edges.isEmpty()) {
			Edge edge = edges.poll();

			if (union(edge.a, edge.b)) {
				costSum += edge.cost;
				connectedEdgeCount++;

				if (connectedEdgeCount == rowSize * colSize - 1) {
					return costSum;
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
		else if (rank[pb] < rank[pa]) {
			parent[pb] = pa;
		}
		else {
			parent[pb] = pa;
			rank[pa]++;
		}

		return true;
	}
}
