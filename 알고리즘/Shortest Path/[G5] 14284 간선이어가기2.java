import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_14284_간선이어가기2 {

	static BufferedReader br;
	static StringTokenizer st;

	static class Edge implements Comparable<Edge> {
		int node, cost;

		public Edge(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
	}

	static int nodeCount, edgeCount;
	static int start, end;
	static ArrayList<ArrayList<Edge>> edges;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getMinCost());
	}

	static int getMinCost() {
		PriorityQueue<Edge> toVisit = new PriorityQueue<>();
		toVisit.add(new Edge(start, 0));

		int[] distance = new int[nodeCount + 1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start] = 0;

		while (!toVisit.isEmpty()) {
			Edge now = toVisit.poll();

			if (distance[now.node] > now.cost) {
				continue;
			}

			for (Edge next : edges.get(now.node)) {
				int nextCost = now.cost + next.cost;
				if (distance[next.node] <= nextCost) {
					continue;
				}
				distance[next.node] = nextCost;
				toVisit.add(new Edge(next.node, nextCost));
			}
		}

		return distance[end];
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		nodeCount = Integer.parseInt(st.nextToken());
		edgeCount = Integer.parseInt(st.nextToken());

		edges = new ArrayList<>();
		for (int node = 0; node <= nodeCount; node++) {
			edges.add(new ArrayList<>());
		}

		for (int edge = 0; edge < edgeCount; edge++) {
			st = new StringTokenizer(br.readLine());

			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			edges.get(start).add(new Edge(end, cost));
			edges.get(end).add(new Edge(start, cost));
		}

		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
	}
}
