import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static class Node implements Comparable<Node> {
		int end;
		int cost;

		public Node(int end, int cost) {
			this.end = end;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}

	static int N, M, R;
	static int[] itemCount;
	static boolean[] visited;
	static ArrayList<ArrayList<Node>> graph;
	static int max = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		itemCount = new int[N];
		graph = new ArrayList<>();
		for (int n = 0; n < N; n++) {
			graph.add(new ArrayList<>());
		}
		st = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++) {
			itemCount[n] = Integer.parseInt(st.nextToken());
		}

		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());
			graph.get(start).add(new Node(end, cost));
			graph.get(end).add(new Node(start, cost));
		}

		for (int n = 0; n < N; n++) {
			max = Math.max(max, solve(n));
		}

		System.out.print(max);
	}

	static int solve(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));
		visited = new boolean[N];

		int ret = 0;
		while (!pq.isEmpty()) {
			Node node = pq.poll();

			if (visited[node.end]) {
				continue;
			}
			visited[node.end] = true;
			ret += itemCount[node.end];

			for (Node next : graph.get(node.end)) {
				if (!visited[next.end]) {
					if (node.cost + next.cost <= M) {
						pq.add(new Node(next.end, node.cost + next.cost));
					}
				}
			}
		}

		return ret;
	}
}
