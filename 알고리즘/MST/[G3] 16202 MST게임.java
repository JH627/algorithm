import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_16202_MST게임 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static class Edge implements Comparable<Edge> {
		int from, node, cost;

		public Edge(int from, int node, int cost) {
			this.from = from;
			this.node = node;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
	}

	static int nodeCount, edgeCount, turnCount;
	static ArrayList<Edge> edges;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		init();
		simulate();
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		nodeCount = Integer.parseInt(st.nextToken());
		edgeCount = Integer.parseInt(st.nextToken());
		turnCount = Integer.parseInt(st.nextToken());

		edges = new ArrayList<>();

		for (int edge = 0; edge < edgeCount; edge++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			edges.add(new Edge(a, b, edge + 1));
		}
	}

	static void simulate() {
		sb = new StringBuilder();

		boolean impossible = false;
		for (int turn = 0; turn < turnCount; turn++) {
			if (impossible) {
				sb.append(0).append(' ');
				continue;
			}

			int result = getMSTCost(turn);

			if (result == 0) {
				impossible = true;
			}

			sb.append(result).append(' ');
		}

		System.out.print(sb);
	}

	static int getMSTCost(int startNode) {
		parent = new int[nodeCount + 1];
		for (int node = 1; node <= nodeCount; node++) {
			parent[node] = node;
		}

		int costSum = 0;
		int edgeUsed = 0;

		for (int node = startNode; node < edgeCount; node++) {
			Edge edge = edges.get(node);

			if (union(edge.from, edge.node)) {
				costSum += edge.cost;
				edgeUsed++;

				if (edgeUsed == nodeCount - 1) {
					return costSum;
				}
			}
		}

		return 0;
	}

	static int find(int x) {
		if (parent[x] == x) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}

	static boolean union(int a, int b) {
		int pa = find(a);
		int pb = find(b);

		if (pa == pb) {
			return false;
		}

		parent[pb] = pa;
		return true;
	}
}
