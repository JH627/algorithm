import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_1719_택배 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static final int INF = 1000000000;

	static class Edge {
		int end, cost;

		public Edge(int end, int cost) {
			this.end = end;
			this.cost = cost;
		}
	}

	static int nodeCount, edgeCount;
	static ArrayList<ArrayList<Edge>> graph;
	static int[][] dist, shortestPath;

	public static void main(String[] args) throws IOException {
		init();
		findPath();
		printPath();
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		nodeCount = Integer.parseInt(st.nextToken());
		edgeCount = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>();
		for (int node = 0; node < nodeCount + 1; node++) {
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

		shortestPath = new int[nodeCount][nodeCount];

		dist = new int[nodeCount][nodeCount];
		for (int a = 0; a < nodeCount; a++) {
			for (int b = 0; b < nodeCount; b++) {
				dist[a][b] = (a == b) ? 0 : INF;
			}
		}
	}

	static void findPath() {
		for (int node = 0; node < nodeCount; node++) {
			for (Edge edge : graph.get(node)) {
				if (dist[node][edge.end] > edge.cost) {
					dist[node][edge.end] = edge.cost;
					shortestPath[node][edge.end] = edge.end;
				}
			}
		}

		for (int mid = 0; mid < nodeCount; mid++) {
			for (int start = 0; start < nodeCount; start++) {
				for (int end = 0; end < nodeCount; end++) {
					if (dist[start][mid] == INF || dist[mid][end] == INF) {
						continue;
					}

					if (dist[start][end] > dist[start][mid] + dist[mid][end]) {
						dist[start][end] = dist[start][mid] + dist[mid][end];
						shortestPath[start][end] = shortestPath[start][mid];
					}
				}
			}
		}
	}

	static void printPath() {
		sb = new StringBuilder();
		for (int row = 0; row < nodeCount; row++) {
			for (int col = 0; col < nodeCount; col++) {
				sb.append(row == col ? "-" : shortestPath[row][col] + 1).append(" ");
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}
}
