import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1507_궁금한민호 {

	static BufferedReader br;
	static StringTokenizer st;

	static final int INF = 10000000;

	static class Edge implements Comparable<Edge> {
		int start, end;
		int dist;

		public Edge(int start, int end, int dist) {
			this.start = start;
			this.end = end;
			this.dist = dist;
		}

		@Override
		public int compareTo(Edge o) {
			return this.dist - o.dist;
		}
	}

	static int nodeCount;
	static int[][] distance;
	static int originDistanceSum, currentDistanceSum;
	static PriorityQueue<Edge> edges;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getMinDistance());
	}

	static int getMinDistance() {
		int distanceSum = 0;

		while (!edges.isEmpty()) {
			Edge now = edges.poll();

			if (distance[now.start][now.end] <= now.dist) {
				continue;
			}

			distanceSum += now.dist;

			currentDistanceSum -= (distance[now.start][now.end] - now.dist) * 2;
			distance[now.start][now.end] = now.dist;
			distance[now.end][now.start] = now.dist;

			for (int mid = 0; mid < nodeCount; mid++) {
				for (int start = 0; start < nodeCount; start++) {
					for (int end = 0; end < nodeCount; end++) {
						if (distance[start][end] > distance[start][mid] + distance[mid][end]) {
							currentDistanceSum -= distance[start][end] - (distance[start][mid] + distance[mid][end]);
							distance[start][end] = distance[start][mid] + distance[mid][end];
						}
					}
				}
			}
		}

		return (currentDistanceSum < originDistanceSum) ? -1 : distanceSum;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		nodeCount = Integer.parseInt(br.readLine());

		distance = new int[nodeCount][nodeCount];

		edges = new PriorityQueue<>();
		originDistanceSum = 0;
		currentDistanceSum = 0;
		for (int start = 0; start < nodeCount; start++) {
			st = new StringTokenizer(br.readLine());
			for (int end = 0; end < nodeCount; end++) {
				int dist = Integer.parseInt(st.nextToken());

				if (start == end) {
					continue;
				}

				edges.add(new Edge(start, end, dist));
				originDistanceSum += dist;

				distance[start][end] = INF;
				currentDistanceSum += INF;
			}
		}
	}
}
