import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class c {

	static BufferedReader br;
	static StringTokenizer st;

	static class Edge {
		int node, distance;

		public Edge(int node, int distance) {
			this.node = node;
			this.distance = distance;
		}
	}

	static class Way implements Comparable<Way> {
		int node, selectedCount;
		long distance;

		public Way(int node, long distance, int selectedCount) {
			this.node = node;
			this.distance = distance;
			this.selectedCount = selectedCount;
		}

		@Override
		public int compareTo(Way o) {
			return Long.compare(this.distance, o.distance);
		}
	}

	static int nodeCount, edgeCount, selectLimit;
	static ArrayList<ArrayList<Edge>> edges;

	static long[][] distance;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getMinDistance());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		nodeCount = Integer.parseInt(st.nextToken());
		edgeCount = Integer.parseInt(st.nextToken());
		selectLimit = Integer.parseInt(st.nextToken());

		edges = new ArrayList<>();
		for (int node = 0; node < nodeCount + 1; node++) {
			edges.add(new ArrayList<>());
		}

		for (int edge = 0; edge < edgeCount; edge++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());

			edges.get(a).add(new Edge(b, dist));
			edges.get(b).add(new Edge(a, dist));
		}

		distance = new long[nodeCount + 1][selectLimit + 1];
		for (int node = 0; node < nodeCount + 1; node++) {
			Arrays.fill(distance[node], Long.MAX_VALUE);
		}
	}

	static long getMinDistance() {
		PriorityQueue<Way> toVisit = new PriorityQueue<>();

		Arrays.fill(distance[1], 0);
		toVisit.add(new Way(1, 0, 0));
		while (!toVisit.isEmpty()) {
			Way now = toVisit.poll();

			if (now.node == nodeCount) {
				return now.distance;
			}

			if (distance[now.node][now.selectedCount] < now.distance) {
				continue;
			}

			for (Edge next : edges.get(now.node)) {
				if (distance[next.node][now.selectedCount] > now.distance + next.distance) {
					distance[next.node][now.selectedCount] = now.distance + next.distance;
					toVisit.add(new Way(next.node, distance[next.node][now.selectedCount], now.selectedCount));
				}

				if (now.selectedCount < selectLimit && distance[next.node][now.selectedCount + 1] > now.distance) {
					distance[next.node][now.selectedCount + 1] = now.distance;
					toVisit.add(new Way(next.node, distance[next.node][now.selectedCount + 1], now.selectedCount + 1));
				}
			}
		}

		return 0;
	}
}
