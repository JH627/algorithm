import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1185_유럽여행 {

	static BufferedReader br;
	static StringTokenizer st;

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

	static int nodeCount, edgeCount;
	static int[] nodeCost;
    static int minNodeCost;
    
    static PriorityQueue<Edge> edges;
	static int[] parent, rank;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(findMinCost());
	}

	static int findMinCost() {
		int costSum = 0;

		int selectedEdgeCount = 0;
		while (!edges.isEmpty()) {
			Edge edge = edges.poll();

			if (!union(edge.a, edge.b)) {
				continue;
			}

			costSum += edge.cost;

			if (++selectedEdgeCount == nodeCount - 1) {
				break;
			}
		}

		return costSum + minNodeCost;
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
			parent[pb] = pa;
			rank[pa]++;
		}
		return true;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		nodeCount = Integer.parseInt(st.nextToken());
		edgeCount = Integer.parseInt(st.nextToken());

		parent = new int[nodeCount];
		for (int node = 0; node < nodeCount; node++) {
			parent[node] = node;
		}
		rank = new int[nodeCount];

		minNodeCost = Integer.MAX_VALUE;
		nodeCost = new int[nodeCount];
		for (int node = 0; node < nodeCount; node++) {
			nodeCost[node] = Integer.parseInt(br.readLine());
			minNodeCost = Math.min(minNodeCost, nodeCost[node]);
		}

		edges = new PriorityQueue<>();
		for (int edge = 0; edge < edgeCount; edge++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());
			edges.add(new Edge(a, b, cost * 2 + nodeCost[a] + nodeCost[b]));
		}
	}
}
