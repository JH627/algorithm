import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static final int INF = 100000;
	static int N, D;
	static ArrayList<ArrayList<Edge>> arr;
	static int[] dist;

	static class Edge implements Comparable<Edge> {
		int end, cost;

		public Edge(int end, int cost) {
			this.end = end;
			this.cost = cost;
		}

		public int compareTo(Edge o) {
			return cost - o.cost;
		}
	}

	public static void main(String[] args) throws IOException {
		init();

		PriorityQueue<Edge> q = new PriorityQueue<>();
		q.offer(new Edge(0, 0));
		dist[0] = 0;

		while (!q.isEmpty()) {
			Edge now = q.poll();

			if (now.cost > dist[now.end]) {
				continue;
			}

			if (now.end == D) {
				break;
			}

			for (Edge edge : arr.get(now.end)) {
				if (dist[edge.end] > now.cost + edge.cost) {
					dist[edge.end] = now.cost + edge.cost;
					q.add(new Edge(edge.end, now.cost + edge.cost));
				}
			}

			if (now.end + 1 > D) {
				continue;
			}

			if (dist[now.end] + 1 < dist[now.end + 1]) {
				dist[now.end + 1] = dist[now.end] + 1;
				q.add(new Edge(now.end + 1, dist[now.end] + 1));
			}
		}

		System.out.print(dist[D]);
	}

	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		arr = new ArrayList<>();
		for (int i = 0; i < D + 1; i++) {
			arr.add(new ArrayList<>());
		}

		dist = new int[D + 1];
		Arrays.fill(dist, INF);

		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			if (start >= D || end > D || end - start < cost) {
				continue;
			}

			arr.get(start).add(new Edge(end, cost));
		}
	}
}
