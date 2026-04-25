import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_1045_도로 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static class Edge {
		int a, b;

		public Edge(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}

	static int nodeCount, selectedEdgeCount;
	static ArrayList<Edge> edges;
	static int[] parent, degree;

	public static void main(String[] args) throws IOException {
		init();
		if (getMaxPriorityEdges() == -1) {
			System.out.print(-1);
			return;
		}
		printDegree();
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());

		nodeCount = Integer.parseInt(st.nextToken());
		selectedEdgeCount = Integer.parseInt(st.nextToken());

		edges = new ArrayList<>();

		for (int a = 0; a < nodeCount; a++) {
			String line = br.readLine();
			for (int b = a + 1; b < nodeCount; b++) {
				if (line.charAt(b) == 'Y') {
					edges.add(new Edge(a, b));
				}
			}
		}

		parent = new int[nodeCount];
		for (int node = 0; node < nodeCount; node++) {
			parent[node] = node;
		}

		degree = new int[nodeCount];
	}

	static int getMaxPriorityEdges() {
		if (edges.size() < selectedEdgeCount) {
			return -1;
		}

		boolean[] selected = new boolean[edges.size()];
		int count = 0;

		for (int edge = 0; edge < edges.size(); edge++) {
			Edge e = edges.get(edge);

			if (union(e.a, e.b)) {
				selected[edge] = true;
				degree[e.a]++;
				degree[e.b]++;
				count++;
			}

			if (count == nodeCount - 1) {
				break;
			}
		}

		if (count < nodeCount - 1) {
			return -1;
		}

		for (int edge = 0; edge < edges.size() && count < selectedEdgeCount; edge++) {
			if (!selected[edge]) {
				Edge e = edges.get(edge);
				selected[edge] = true;
				degree[e.a]++;
				degree[e.b]++;
				count++;
			}
		}

		return count;
	}

	static void printDegree() {
		sb = new StringBuilder();
		for (int node = 0; node < nodeCount; node++) {
			sb.append(degree[node]).append(' ');
		}
		System.out.print(sb);
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

		if (pa < pb) {
			parent[pb] = pa;
		}
		else {
			parent[pa] = pb;
		}

		return true;
	}
}
