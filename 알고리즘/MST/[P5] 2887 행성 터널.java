import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_2887_행성터널 {

	static BufferedReader br;
	static StringTokenizer st;

	static class Node {
		int x, y, z;
		int index;

		public Node(int x, int y, int z, int index) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.index = index;
		}
	}

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
	static Node[] nodes;
	static ArrayList<Edge> edges;
	static int[] parent, rank;


	public static void main(String[] args) throws IOException {
		if (!init()) {
			System.out.println(0);
			return;
		}

		addEdges();

		System.out.print(getMinDistance());
	}

	static void addEdges() {
		Arrays.sort(nodes, Comparator.comparingInt(o -> o.x));
		for (int index = 0; index < nodeCount - 1; index++) {
			int distance = Math.abs(nodes[index + 1].x - nodes[index].x);
			edges.add(new Edge(nodes[index].index, nodes[index + 1].index, distance));
		}

		Arrays.sort(nodes, Comparator.comparingInt(o -> o.y));
		for (int index = 0; index < nodeCount - 1; index++) {
			int distance = Math.abs(nodes[index + 1].y - nodes[index].y);
			edges.add(new Edge(nodes[index].index, nodes[index + 1].index, distance));
		}

		Arrays.sort(nodes, Comparator.comparingInt(o -> o.z));
		for (int index = 0; index < nodeCount - 1; index++) {
			int distance = Math.abs(nodes[index + 1].z - nodes[index].z);
			edges.add(new Edge(nodes[index].index, nodes[index + 1].index, distance));
		}
	}

	static int getMinDistance() {
		Collections.sort(edges);

		int sum = 0;
		int connectedNodeCount = 0;
		for (Edge edge : edges) {
			if (!union(edge.a, edge.b)) {
				continue;
			}

			sum += edge.cost;
			if (++connectedNodeCount == nodeCount - 1) {
				break;
			}
		}

		return sum;
	}

	static int find(int x) {
		if (parent[x] == x) {
			return x;
		}

		return parent[x] = find(parent[x]);
	}

	static boolean union(int x, int y) {
		int px = find(x);
		int py = find(y);

		if (px == py) {
			return false;
		}

		if (rank[px] < rank[py]) {
			parent[px] = py;
		}
		else if (rank[px] > rank[py]) {
			parent[py] = px;
		}
		else {
			parent[py] = px;
			rank[px]++;
		}
		return true;
	}

	static boolean init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		nodeCount = Integer.parseInt(br.readLine());

		nodes = new Node[nodeCount];
		for (int node = 0; node < nodeCount; node++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			nodes[node] = new Node(x, y, z, node);
		}

		edges = new ArrayList<>();
		parent = new int[nodeCount];
		rank = new int[nodeCount];

		for (int index = 0; index < nodeCount; index++) {
			parent[index] = index;
		}

		return (nodeCount != 1);
	}
}
