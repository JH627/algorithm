import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_14950_정복자 {

	static BufferedReader br;
	static StringTokenizer st;

	static class Road implements Comparable<Road> {
		int a, b;
		int cost;

		public Road(int a, int b, int cost) {
			this.a = a;
			this.b = b;
			this.cost = cost;
		}

		@Override
		public int compareTo(Road o) {
			return this.cost - o.cost;
		}
	}

	static int nodeCount, edgeCount, roadCost;
	static PriorityQueue<Road> edges;

	static int[] parent, rank;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getMinRoadCost());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		nodeCount = Integer.parseInt(st.nextToken());
		edgeCount = Integer.parseInt(st.nextToken());
		roadCost = Integer.parseInt(st.nextToken());

		rank = new int[nodeCount];
		parent = new int[nodeCount];
		for (int node = 0; node < nodeCount; node++) {
			parent[node] = node;
		}

		edges = new PriorityQueue<>();
		for (int edge = 0; edge < edgeCount; edge++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());

			edges.add(new Road(a, b, cost));
		}
	}

	static int getMinRoadCost() {
		int costSum = 0;
		int connectedNodeCount = 0;
		int currentRoadCost = 0;

		while (!edges.isEmpty()) {
			Road road = edges.poll();

			if (union(road.a, road.b)) {
				connectedNodeCount++;

				costSum += currentRoadCost + road.cost;
				currentRoadCost += roadCost;
			}

			if (connectedNodeCount == nodeCount - 1) {
				break;
			}
		}

		return costSum;
	}

	static int find(int x) {
		if (x == parent[x]) {
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

		if (rank[pa] < rank[pb]) {
			parent[pa] = pb;
		}
		else if (rank[pa] > rank[pb]) {
			parent[pb] = pa;
		}
		else {
			parent[pa] = pb;
			rank[pb]++;
		}

		return true;
	}

}
