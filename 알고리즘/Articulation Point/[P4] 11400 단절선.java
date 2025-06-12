import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_11400_단절선 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static class Edge implements Comparable<Edge> {
		int start, end;
		public Edge(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Edge o) {
			if (this.start == o.start) {
				return this.end - o.end;
			}
			return this.start - o.start;
		}
	}

	static int vertexCount, edgeCount;
	static ArrayList<ArrayList<Integer>> graph;
	static ArrayList<Edge> bridges;
	static int[] sequence, low;
	static int clock = 0;

	public static void main(String[] args) throws IOException {
		init();
		findBridge(0, -1);
		makeAnswer();
		System.out.print(sb);
	}

	static void findBridge(int now, int parent) {
		sequence[now] = low[now] = ++clock;

		for (Integer next : graph.get(now)) {
			if (next == parent) {
				continue;
			}

			// 방문했던 노드라면 올라갈 수 있는 최소 값 갱신
			if (sequence[next] != 0) {
				low[now] = Math.min(low[now], sequence[next]);
			}
			// 방문 안 했던 노드라면
			else {
				// 방문해봄
				findBridge(next, now);
				// 근데 만약 다음 노드가 지금 내 노드 보다는 위로 올라갈 수 없는 경우
				// 단절선에 추가
				if (low[next] > sequence[now]) {
					bridges.add(new Edge(Math.min(now, next) + 1, Math.max(now, next) + 1));
				}
				// 해당 정점에서 올라갈 수 있는 곳 갱신
				low[now] = Math.min(low[now], low[next]);
			}
		}
	}

	static void makeAnswer() {
		Collections.sort(bridges);
		sb.append(bridges.size()).append("\n");
		for (Edge bridge : bridges) {
			sb.append(bridge.start).append(" ").append(bridge.end).append("\n");
		}
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		vertexCount = Integer.parseInt(st.nextToken());
		edgeCount = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>();
		for (int vertex = 0; vertex < vertexCount; vertex++) {
			graph.add(new ArrayList<>());
		}

		for (int edge = 0; edge < edgeCount; edge++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			graph.get(a).add(b);
			graph.get(b).add(a);
		}

		bridges = new ArrayList<>();

		sequence = new int[vertexCount];
		low = new int[vertexCount];

		clock = 0;
	}
}
