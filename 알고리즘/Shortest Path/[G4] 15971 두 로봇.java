import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_15971_두로봇 {

	static BufferedReader br;
	static StringTokenizer st;

	static class Edge implements Comparable<Edge> {
		int node, cost, maxCost;

		public Edge(int node, int cost, int maxCost) {
			this.node = node;
			this.cost = cost;
			this.maxCost = maxCost;
		}

		@Override
		public int compareTo(Edge o) {
			return cost - o.cost;
		}
	}

	static int nodeCount, edgeCount;
	static int aPoint, bPoint;

	static ArrayList<ArrayList<Edge>> graph;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(findMinCost());
	}

	static int findMinCost() {
		PriorityQueue<Edge> toVisit = new PriorityQueue<>();
		toVisit.add(new Edge(aPoint, 0, 0));
		boolean[] visited = new boolean[nodeCount + 1];

		while (!toVisit.isEmpty()) {
			Edge now = toVisit.poll();
			if (visited[now.node]) {
				continue;
			}

			if (now.node == bPoint) {
				return now.cost - now.maxCost;
			}

			visited[now.node] = true;

			for (Edge next : graph.get(now.node)) {
				if (!visited[next.node]) {
					toVisit.add(new Edge(next.node, now.cost + next.cost, Math.max(next.maxCost, now.maxCost)));
				}
			}
		}

		return -1;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		nodeCount = Integer.parseInt(st.nextToken());
		edgeCount = nodeCount - 1;

		aPoint = Integer.parseInt(st.nextToken());
		bPoint = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>();
		for (int node = 0; node <= nodeCount; node++) {
			graph.add(new ArrayList<>());
		}

		for (int edge = 0; edge < edgeCount; edge++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph.get(a).add(new Edge(b, cost, cost));
			graph.get(b).add(new Edge(a, cost, cost));
		}
	}
}
