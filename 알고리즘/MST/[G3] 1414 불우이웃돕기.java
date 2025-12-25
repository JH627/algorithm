import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ_1414_불우이웃돕기 {

	static BufferedReader br;

	static class Edge implements Comparable<Edge> {
		int start, end;
		int cost;

		public Edge(int start, int end, int cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
	}

	static int nodeCount, lineSum;
	static int[] parent, rank;
	static PriorityQueue<Edge> edges;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getRemainLineLength());
	}

	static int getRemainLineLength() {
		int checkedNodeCount = 0;
		while (!edges.isEmpty()) {
			Edge edge = edges.poll();

			if (!union(edge.start, edge.end)) {
				continue;
			}

			lineSum -= edge.cost;

			if (++checkedNodeCount == nodeCount - 1) {
				break;
			}
		}

		return checkedNodeCount == nodeCount - 1 ? lineSum : -1;
	}

	static int find(int node) {
		if (parent[node] == node) {
			return node;
		}

		return parent[node] = find(parent[node]);
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
		nodeCount = Integer.parseInt(br.readLine());

		parent = new int[nodeCount];
		rank = new int[nodeCount];
		for (int node = 0; node < nodeCount; node++) {
			parent[node] = node;
		}

		lineSum = 0;
		edges = new PriorityQueue<>();
		for (int start = 0; start < nodeCount; start++) {
			char[] line = br.readLine().toCharArray();
			for (int end = 0; end < nodeCount; end++) {
				if (line[end] == '0') {
					continue;
				}
				int cost = (Character.isUpperCase(line[end]) ? line[end] - 'A' + 27 : line[end] - 'a' + 1);
				lineSum += cost;

				if (start != end) {
					edges.add(new Edge(start, end, cost));
				}
			}
		}
	}


}
