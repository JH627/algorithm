import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// 문제 분석
// 1. Each pair of cities is connected by at most one road, that is, there are no multiple edges between the same pair of cities.
// 각 정점 간 간선은 최대 하나
// 그에 맞는 조건 최대 간선의 수 = nC2 (== min(100000, n(n-1)/2)

// 2. It is guaranteed that at least one MST always exists for the given input.
// 최소한 하나의 MST가 그래프 내에 존재
//	=> 전체 그래프는 연결 그래프
//		어떤 방식으로 간선을 선택하든, 연결만 잘 유지하면 MST 구성 가능
// 		연결되지 않은 정점들을 가장 비용이 낮은 간선들로 연결해가면 MST만들 수 있음

// 1, 2를 통해 그리디하게 MST를 만들어 볼 수 있음
// 	=> MST 생성 알고리즘 중 kruskal을 선택하는 이유

//  =================================================

// 풀이 로직
// 1. 간선을 받아서 cost 오름차순으로 정렬
// 2. cost가 같은 간선끼리 묶어서 한번에 처리
// 	2-1. 먼저 간선의 두 정점이 같은 집합에 속해 있는지 확인 (기존 MST, 임시 MST아님)
//		2-1-1. 같은 집합에 있다면 이 간선은 MST에 포함 불가 => 타입 3
//	2-2. 정점들을 대상으로 임시 그래프를 구성
//	2-3. 임시 그래프 내에서 브릿지 판별 (타잔 알고리즘 중 브릿지 판별 로직 사용)
//		2-3-1. 브릿지면 반드시 포함되어야 함 => 타입 1 (상세한건 dfs()함수에 기술함)
//	2-4. 이후 비용이 같은 간선들을 모두 기존 MST에 합침
//		(비용이 같은 간선 내에서는 순서 상관 없음)
// 3. 각 간선의 타입 출력

// =================================================

// 최적화 방안
// Kruskal 알고리즘의 최적화 방식 중 두 가지를 사용
// 1. 경로 압축 기법 (parent[])
// find() 함수에서 각 노드가 루트 노드를 직접 가리킬 수 있도록 만든들어 줌
// 최악의 경우 => O(1)에 가깝게 줄여줌

// 2. Union by rank 기법 (rnk[] 사용)
// 항상 랭크 (트리 높이)가 더 낮은 집합를 높은 집합 밑에 넣는 방법
// 트리의 높이를 최소화 해서 find 연산 시 루트까지의 거리 줄임

// 경로 압축과 함께 사용하면 parent를 찾을 때
// 모든 union-find 연산이 O(α(N)) (a는 아커만 함수) 시간에 수행
// 2번은 최적화 방식에서 필수는 아님 오히려 오버헤드가 생기는 경우가 있음
// 1번은 필수

public class BOJ_32824_NewMegacity {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static class Edge implements Comparable<Edge> {
		int start, end;
		int cost;
		int originalIndex;

		public Edge(int start, int end, int cost, int originalIndex) {
			this.start = start;
			this.end = end;
			this.cost = cost;
			this.originalIndex = originalIndex;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
	}

	static class Pair {
		int v, id;

		Pair(int v, int id) {
			this.v = v; this.id = id;
		}
	}

	static class MST {
		int[] parent, rank;

		MST(int vertexCount) {
			parent = new int[vertexCount];
			rank = new int[vertexCount];
			for (int vertexIndex = 0; vertexIndex < vertexCount; vertexIndex++) {
				parent[vertexIndex] = vertexIndex;
			}
		}

		int find(int x) {
			if (parent[x] == x) {
				return x;
			}
			return parent[x] = find(parent[x]);
		}

		void union(int a, int b) {
			int pa = find(a);
			int pb = find(b);

			if (pa == pb) {
				return;
			}

			if (rank[pa] < rank[pb]) {
				parent[pa] = pb;
			} else if (rank[pa] > rank[pb]) {
				parent[pb] = pa;
			} else {
				parent[pb] = pa;
				rank[pa]++;
			}
		}
	}

	static int vertexCount, edgeCount;
	static ArrayList<Edge> edges; // 원본 간선

	static int[] type; // 간선 타입
	static boolean[] visited, bridge;
	static int[] sequence, low;

	static int clock;

	public static void main(String[] args) throws IOException {
		init();
		makeMST();
		printResult();
	}

	static void makeMST() {
		MST globalMST = new MST(vertexCount);

		for (int edgeIndex = 0; edgeIndex < edgeCount; edgeIndex++) {
			int nowIndex = edgeIndex;
			int nowCost = edges.get(nowIndex).cost;

			// 비용이 같은 간선 찾기
			while (nowIndex < edgeCount && edges.get(nowIndex).cost == nowCost) {
				nowIndex++;
			}

			// 같은 비용 그룹 내 처리
			ArrayList<Integer> candidates = new ArrayList<>();
			for (int tempIndex = edgeIndex; tempIndex < nowIndex; tempIndex++) {
				Edge nowEdge = edges.get(tempIndex);
				if (globalMST.find(nowEdge.start) == globalMST.find(nowEdge.end)) {
					// 사용 불가
					type[nowEdge.originalIndex] = 3;
				}
				else {
					candidates.add(tempIndex);
				}
			}

			if (!candidates.isEmpty()) {
				// 로컬 그래프
				Map<Integer, Integer> map = new HashMap<>();
				int id = 0;
				for (int candidateIndex : candidates) {
					Edge tempEdge = edges.get(candidateIndex);
					map.putIfAbsent(globalMST.find(tempEdge.start), id++);
					map.putIfAbsent(globalMST.find(tempEdge.end), id++);
				}

				ArrayList<ArrayList<Pair>> graph = new ArrayList<>();
				for (int vertexIndex = 0; vertexIndex < id; vertexIndex++) {
					graph.add(new ArrayList<>());
				}

				for (int candidateIndex = 0; candidateIndex < candidates.size(); candidateIndex++) {
					Edge edge = edges.get(candidates.get(candidateIndex));
					int a = map.get(globalMST.find(edge.start));
					int b = map.get(globalMST.find(edge.end));
					graph.get(a).add(new Pair(b, candidateIndex));
					graph.get(b).add(new Pair(a, candidateIndex));
				}

				// 브리지 탐색
				clock = 0;
				visited = new boolean[id];
				sequence = new int[id];	low = new int[id];
				bridge = new boolean[candidates.size()];
				for (int index = 0; index < graph.size(); index++) {
					if (!visited[index]) {
						findBridge(index, -1, graph);
					}
				}

				for (int index = 0; index < candidates.size(); index++) {
					type[edges.get(candidates.get(index)).originalIndex] = bridge[index] ? 1 : 2;
				}
			}

			// 원본 MST에 합치기
			for (int index = edgeIndex; index < nowIndex; index++) {
				Edge edge = edges.get(index);
				globalMST.union(edge.start, edge.end);
			}

			edgeIndex = nowIndex - 1;
		}
	}

	// Tarjan bridge
	// 브릿지가 될라면 그 간선을 없애면 그래프가 두 개로 나눠져야함
	// 브릿지가 되려면 다음에 방문하는 노드에서, 지금 노드보다 이전에 방문한 노드를 갈 수 있으면 안됨
	static void findBridge(int now, int parent, ArrayList<ArrayList<Pair>> graph) {
		visited[now] = true;
		// 1차적으로 방문 순서 저장
		sequence[now] = low[now] = clock++;
		for (Pair next : graph.get(now)) {
			// 역으로 올라가지는 않음
			if (next.id == parent) {
				continue;
			}

			// 방문했던 노드라면 최대로 올라갈 수 있는 노드값만 갱신
			if (visited[next.v]) {
				// 부모로 가는 간선을 제외한, 이미 방문한 정점으로 가는 간선을 만났을 경우 (== Back Edge)
				// 	=> 이 경우 현재 노드에서 더 이전에 방문한 정점까지 도달할 수 있으므로
				// 	=> 현재 노드의 low 값을 갱신해준다 (가장 빨리 방문했던 노드)
				low[now] = Math.min(low[now], sequence[next.v]);
			}
			else {
				findBridge(next.v, next.id, graph);
				// 근데 방문하고 돌아오니
				// 자식 노드에서 최대로 올라갈수 있는 노드가 나보다 늦게 방문하는 노드인 경우 
				// 이건 브릿지가 됨 
				// ==========================
				// 브릿지가 정답 MST에 무조건 포함이 되는 이유
				// 브릿지가 아닌 경우
				// 	해당 정점으로 어떻게라도 돌아서 갈 수 있는 간선이 있음
				//		비용이 같은 간선끼리 묶어 놓았기 때문에 돌아서 가는 경우의 수는 각자 다른 경우의 수가 됨
				//		=> 포함이 될수도 안될수도 있음 => 2번타입
				// 브릿지인 경우
				//	모든 경우의 수 MST에서 반드시 포함되는 MST임
				// 		해당 간선을 포함하지 않고는 돌아갈 수 있는 방법이 없기 때문
				// 			이걸 빼면 그래프가 애초에 2개로 나눠짐 
				if (low[next.v] > sequence[now]) {
					bridge[next.id] = true;
				}
				// 해당 정점에서 가장 이전으로 돌아갈수 있는 곳 확인
				low[now] = Math.min(low[now], low[next.v]);
			}
		}
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		vertexCount = Integer.parseInt(st.nextToken());
		edgeCount = Integer.parseInt(st.nextToken());

		// 간선 리스트 읽기
		edges = new ArrayList<>();
		for (int edgeIndex = 0; edgeIndex < edgeCount; edgeIndex++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());
			edges.add(new Edge(start, end, cost, edgeIndex));
		}

		// 비용 오름차순 정렬
		Collections.sort(edges);

		type = new int[edgeCount];
	}

	static void printResult() {
		for (int edgeIndex = 0; edgeIndex < edgeCount; edgeIndex++) {
			sb.append(type[edgeIndex]).append("\n");
		}
		System.out.print(sb);
	}
}
