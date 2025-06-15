import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 단절점 (Articulation Point)
 * - 무방향 그래프에서 어떤 정점을 제거했을 때,
 *   그래프가 두 개 이상의 연결 요소로 나뉘는 경우 해당 정점을 '단절점'이라고 함
 *
 * 단절점 판별 기준 (DFS 기반 타잔 알고리즘):
 * 1. DFS 트리에서 루트 노드인 경우:
 *    - 자식 노드가 두 개 이상이면 단절점 (필요 조건 O, 충분 조건 X)
 *      - 루트 노드는 DFS 탐색의 시작점이므로, 루트의 각 자식은 서로 다른 서브트리를 형성
 *      - 만약 루트 노드를 제거하면 그 자식 서브트리들은 루트를 통해서만 연결되므로, 루트가 사라지면 그래프가 분리됨
 *      - 따라서 자식이 2개 이상인 루트는 단절점이 될 수 있음
 *      - 단, 자식 수가 2개 이상이어도 자식들이 back edge로 서로 연결되어 있다면 그래프는 분리되지 않음
 *        → 이 경우에도 low[] 값을 통해 정확하게 판단
 *
 * 2. 루트가 아닌 일반 노드인 경우:
 *    - 자식 노드 중 하나라도 low[자식] >= sequence[현재] 이면 단절점
 *      - 이는 자식 서브트리가 현재 노드를 통해서만 루트와 연결된다는 의미
 *
 * 용어
 * - sequence[]: DFS 탐색 순서 (각 정점의 방문 시간)
 * - low[]: 해당 정점 및 자손들이 갈 수 있는 가장 빠른 (작은) sequence 번호
 *
 * 시간복잡도
 * - O(V + E)
 */

public class BOJ_11266_단절점 {

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	static int vertexCount, edgeCount;
	static ArrayList<ArrayList<Integer>> graph;
	static int[] sequence, low;
	static ArrayList<Integer> points;
	static int clock;

	public static void main(String[] args) throws IOException {
		init();
		for (int vertex = 0; vertex < vertexCount; vertex++) {
			if (sequence[vertex] == 0) {
				getPoints(-1, vertex);
			}
		}
		printAnswer();
	}

	static void getPoints(int prev, int now) {
		low[now] = sequence[now] = ++clock;

		int childCount = 0;
		boolean isPoint = false;
		for (Integer next : graph.get(now)) {
			if (next == prev) {
				continue;
			}

			if (sequence[next] != 0) {
				low[now] = Math.min(low[now], sequence[next]);
			}
			else {
				getPoints(now, next);
				childCount++;
				low[now] = Math.min(low[now], low[next]);

				// 루트가 아닌 경우 단절점 조건
				if (prev != -1 && low[next] >= sequence[now]) {
					isPoint = true;
				}
			}
		}

		// 루트인 경우 단절점 조건
		if ((prev == -1 && childCount >= 2) || (prev != -1 && isPoint)) {
			points.add(now);
		}
	}

	static void printAnswer() {
		sb = new StringBuilder();
		Collections.sort(points);
		sb.append(points.size()).append("\n");
		for (Integer point : points) {
			sb.append(point + 1).append(" ");
		}
		System.out.print(sb);
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
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

		sequence = new int[vertexCount];
		low = new int[vertexCount];
		points = new ArrayList<>();

		clock = 0;
	}
}
