import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1833_고속철도설계하기 {

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
			return this.cost - o.cost;
		}
	}

	static int nodeCount;
	static int connectedEdgeCount, costSum;
	static int[] parent, rank;

	static PriorityQueue<Edge> edges;
	static ArrayList<Edge> answer;

	public static void main(String[] args) throws IOException {
		init();
		findMinEdges();
		printEdges();
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		nodeCount = Integer.parseInt(br.readLine());
		edges = new PriorityQueue<>();
		answer = new ArrayList<>();
		parent = new int[nodeCount];
		rank = new int[nodeCount];

		for (int node = 0; node < nodeCount; node++) {
			parent[node] = node;
		}

		costSum = 0;
		connectedEdgeCount = 0;
		for (int a = 0; a < nodeCount; a++) {
			st = new StringTokenizer(br.readLine());
			for (int b = 0; b < nodeCount; b++) {
				int cost = Integer.parseInt(st.nextToken());
				if (a >= b) {
					continue;
				}

				if (cost < 0) {
					if (union(a, b)) {
						connectedEdgeCount++;
					}
					costSum += (-1) * cost;
					continue;
				}

				edges.add(new Edge(a, b, cost));
			}
		}
	}

	static void findMinEdges() {
		while (!edges.isEmpty() && connectedEdgeCount != nodeCount - 1) {
			Edge now = edges.poll();

			if (union(now.a, now.b)) {
				answer.add(now);
				costSum += now.cost;
			}
		}
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

		if (rank[pa] > rank[pb]) {
			parent[pb] = pa;
		}
		else if (rank[pa] < rank[pb]) {
			parent[pa] = pb;
		}
		else {
			parent[pa] = pb;
			rank[pa]++;
		}

		return true;
	}

	static void printEdges() {
		sb = new StringBuilder();
		sb.append(costSum).append(" ").append(answer.size()).append("\n");
		for (Edge edge : answer) {
			sb.append(edge.a + 1).append(" ").append(edge.b + 1).append("\n");
		}
		System.out.print(sb);
	}
}
