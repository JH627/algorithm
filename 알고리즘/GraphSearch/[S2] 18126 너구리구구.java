import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_18126_너구리구구 {

	static BufferedReader br;
	static StringTokenizer st;

	static class Edge {
		int node;
		long cost;

		public Edge(int node, long cost) {
			this.node = node;
			this.cost = cost;
		}
	}

	static int nodeCount, edgeCount;
	static ArrayList<ArrayList<Edge>> graph;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(findMaxLength());
	}

	static long findMaxLength() {
		boolean[] visited = new boolean[nodeCount];
		long max = 0;

		Queue<Edge> toVisit = new LinkedList<>();
		toVisit.add(new Edge(0, 0));
		visited[0] = true;
		while (!toVisit.isEmpty()) {
			Edge now = toVisit.poll();

			max = Math.max(max, now.cost);

			for (Edge next : graph.get(now.node)) {
				if (visited[next.node]) {
					continue;
				}
				toVisit.add(new Edge(next.node, now.cost + next.cost));
				visited[next.node] = true;
			}
		}

		return max;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		nodeCount = Integer.parseInt(br.readLine());
		edgeCount = nodeCount - 1;

		graph = new ArrayList<>();
		for (int node = 0; node < nodeCount; node++) {
			graph.add(new ArrayList<>());
		}

		for (int edge = 0; edge < edgeCount; edge++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());
			graph.get(a).add(new Edge(b, cost));
			graph.get(b).add(new Edge(a, cost));
		}
	}
}
