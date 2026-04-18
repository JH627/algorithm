import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_13911_집구하기 {

	static BufferedReader br;
	static StringTokenizer st;

	static final int NONE = 0;
	static final int MCDONALDS = 1;
	static final int STARBUCKS = 2;
	static final long INF = Long.MAX_VALUE / 4;

	static class Edge implements Comparable<Edge> {
		int end;
		long distance;

		public Edge(int end, long distance) {
			this.end = end;
			this.distance = distance;
		}

		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.distance, o.distance);
		}
	}

	static int nodeCount, edgeCount;
	static ArrayList<ArrayList<Edge>> graph;
	static int[] type;
	static int mcdonaldsDistanceLimit, starbucksDistanceLimit;
	static long[] mcdonaldsDistance, starbucksDistance;
	static ArrayList<Integer> mcdonaldsNodes;
	static ArrayList<Integer> starbucksNodes;
	
	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getMinDistance());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		nodeCount = Integer.parseInt(st.nextToken());
		edgeCount = Integer.parseInt(st.nextToken());

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

		type = new int[nodeCount];

		mcdonaldsNodes = new ArrayList<>();
		starbucksNodes = new ArrayList<>();

		st = new StringTokenizer(br.readLine());
		int mCount = Integer.parseInt(st.nextToken());
		mcdonaldsDistanceLimit = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int mc = 0; mc < mCount; mc++) {
			int node = Integer.parseInt(st.nextToken()) - 1;
			mcdonaldsNodes.add(node);
			type[node] |= MCDONALDS;
		}

		st = new StringTokenizer(br.readLine());
		int sCount = Integer.parseInt(st.nextToken());
		starbucksDistanceLimit = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int st = 0; st < sCount; st++) {
			int node = Integer.parseInt(Main.st.nextToken()) - 1;
			starbucksNodes.add(node);
			type[node] |= STARBUCKS;
		}

		mcdonaldsDistance = dijkstra(mcdonaldsNodes);
		starbucksDistance = dijkstra(starbucksNodes);
	}

	static long[] dijkstra(ArrayList<Integer> sources) {
		long[] dist = new long[nodeCount];
		for (int node = 0; node < nodeCount; node++) {
			dist[node] = INF;
		}

		PriorityQueue<Edge> toVisit = new PriorityQueue<>();
		for (int source : sources) {
			dist[source] = 0;
			toVisit.offer(new Edge(source, 0));
		}

		while (!toVisit.isEmpty()) {
			Edge cur = toVisit.poll();

			if (cur.distance > dist[cur.end]) {
				continue;
			}

			for (Edge next : graph.get(cur.end)) {
				long nextDist = cur.distance + next.distance;

				if (nextDist < dist[next.end]) {
					dist[next.end] = nextDist;
					toVisit.offer(new Edge(next.end, nextDist));
				}
			}
		}

		return dist;
	}

	static long getMinDistance() {
		long answer = INF;

		for (int node = 0; node < nodeCount; node++) {
			if (type[node] != NONE) {
				continue;
			}

			if (mcdonaldsDistance[node] <= mcdonaldsDistanceLimit && starbucksDistance[node] <= starbucksDistanceLimit) {
				answer = Math.min(answer, mcdonaldsDistance[node] + starbucksDistance[node]);
			}
		}

		return answer == INF ? -1 : answer;
	}
}
