import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_17396_백도어 {

	static BufferedReader br;
	static StringTokenizer st;

	static class Node implements Comparable<Node> {
		int node;
		long distance;

		public Node(int node, long distance) {
			this.node = node;
			this.distance = distance;
		}

		@Override
		public int compareTo(Node o) {
			return Long.compare(this.distance, o.distance);
		}
	}

	static int nodeCount, edgeCount;
	static boolean[] blocked;

	static ArrayList<ArrayList<Node>> graph;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(findDistance());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		nodeCount = Integer.parseInt(st.nextToken());
		edgeCount = Integer.parseInt(st.nextToken());

		blocked = new boolean[nodeCount];

		graph = new ArrayList<>();
		for (int node = 0; node < nodeCount; node++) {
			graph.add(new ArrayList<>());
		}

		st = new StringTokenizer(br.readLine());
		for (int node = 0; node < nodeCount; node++) {
			if (st.nextToken().equals("1")) {
				blocked[node] = true;
			}
		}

		blocked[nodeCount - 1] = false;

		for (int edge = 0; edge < edgeCount; edge++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int distance = Integer.parseInt(st.nextToken());

			if (!blocked[a] && !blocked[b]) {
				graph.get(a).add(new Node(b, distance));
				graph.get(b).add(new Node(a, distance));
			}
		}
	}

	static long findDistance() {
		long[] distance = new long[nodeCount];
		Arrays.fill(distance, Long.MAX_VALUE);

		PriorityQueue<Node> toVisit = new PriorityQueue<>();
		distance[0] = 0;
		toVisit.offer(new Node(0, 0));

		while (!toVisit.isEmpty()) {
			Node cur = toVisit.poll();

			if (cur.distance > distance[cur.node]) {
				continue;
			}
			if (cur.node == nodeCount - 1) {
				return cur.distance;
			}

			for (Node next : graph.get(cur.node)) {
				long nextDist = cur.distance + next.distance;

				if (nextDist < distance[next.node]) {
					distance[next.node] = nextDist;
					toVisit.offer(new Node(next.node, nextDist));
				}
			}
		}

		return -1;
	}

}
