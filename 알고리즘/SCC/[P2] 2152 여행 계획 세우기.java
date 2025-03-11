import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * SCC
 * 
 * Strongly Connected Digraph (강한 연결 방향 그래프)
 * - 어떤 두 정점을 잡든 간에 서로 연결이 되어 있는, 방향 경로가 존재하는 그래프
 * 
 * Strongly Connected Component(강한연결 요소)
 * - 어떤 두 정점을 잡든 서로 도달할 수 있는 경로가 있는 부분 방향 그래프
 *   같은 강한 연결 요소에 속하는 정점들은 서로 이동할 수 있는 방향 경로가 반드시 존재함
 *   사이클이 발생한다면 무조건 SCC에 해당하며 강한 연결 방향 그래프는 강한 연결 요소가 오직 하나
 *   SCC를 하나의 정점
 * 
 * 특징 
 * 
 * SCC 집합은 DAG(Directed Acyclic Graph)를 이룸
 * - SCC는 서로 도달할 수 있는 정점들의 집합으로, 하나의 SCC 내에서는 모든 정점들이 서로 연결되어 있음
 * - SCC들을 묶은 메타 그래프를 구성하면, 각 SCC는 하나의 정점처럼 취급됨
 * - 이 메타 그래프에서 각 SCC 간에는 방향이 있는 간선이 존재할 수 있음 
 *   그러나 순환(사이클)이 존재하지 않음. 즉, SCC들 간에는 순환을 만들 수 없다
 * 
 * 왜 순환이 존재하지 않나
 * - SCC를 찾는 과정에서, 각 SCC는 하나의 정점처럼 취급되며, 각 SCC 간에는 순방향 간선만 존재
 * - 만약 두 SCC 사이에 양방향 간선이 존재한다면 두 SCC는 사실상 하나의 SCC로 합쳐져야 하므로
 *   서로 다른 SCC들 사이에는 순방향 간선만 존재하며 역방향 간선은 존재하지 않음
 *   == SCC 집합을 구성하는 메타 그래프는 항상 DAG입니다. 
 * 
 * =========================================================
 * 
 * SCC를 찾는 두 가지 알고리즘 (타잔, 코사라주 알고리즘)
 * 
 * 타잔 (Tarjan) 알고리즘
 * - 모든 정점에 대해 DFS를 수행하여 SCC를 찾는 알고리즘
 * - 방향경로의 시작점으로 다시 돌아갈 수 있어야 같은 SCC에 속하는 것을 이용
 *   같은 SCC에 속하는 노드들은 같은 부모를 갖는다고 보고, 그 SCC에 속한 노드들 중에서 가장 id값이 작은 것을 부모로 지정
 *   => id값은 DFS를 시작할 때 부여
 * 
 * 코사라주(Kosaraju) 알고리즘
 * - 그래프의 간선 방향이 달라도 강한 연결요소는 일치함을 이용
 * - 정방향 그래프에서 DFS를 실행하여 post가 높은 정점이 SCC의 source가 되고
 *   역방향 그래프에서 DFS를 실행하여 post가 높은 정점이 SCC의 sink가 됨
 * - 그래프 상에서의 서로 다른 연결 요소 A, B가 있고, 정점 u는 A에 속하고, 정점 v는 B에 속하며 (u, v)라는 간선이 존재할때  
 *   탐색 종료시간을 B가 A보다 늦음
 * 
 * =========================================================
 * 
 * 타잔 알고리즘 VS 코사라주 알고리즘
 * 공통점
 * - DFS 기반 동작, 시간복잡도 O(V + E)
 * 
 * 타잔
 * - 상대적으로 구현이 더 복잡
 * - 백트래킹을 사용하여 SCC 확인
 * - DFS 탐색 중 스택을 사용하여 SCC를 찾음
 * 
 * 코사라주
 * - 시간복잡도는 똑같이 O(V + E)이지만 두 번의 그래프 순회가 이루어지고
 *   역방향 그래프를 만드는 비용도 발생하기 때문에
 *   타잔 알고리즘에 비해 비효율적이다.
 * - DFS 탐색 중에 스택을 사용하고, 역방향 DFS에 스택을 사용
 * 
 * =========================================================
 * 
 * 타잔 알고리즘 구현
 * 1. 인접 정점에 방문하며 자기 자신을 스택에 넣고, DFS를 재귀적으로 수행
 * 2. 인접 정점에 방문하였으니 아직 처리중인 상태일 경우 더 작은 값으로 부모값을 갱신
 * 3. 부모 노드의 DFS가 끝난 경우에는, 자신의 id값이 스택에서 나올 때까지 스택에 있는 노드들을 pop하고 SCC배열에 추가
 * 4. 그렇게 만들어진 하나의 SCC를 전체 SCC 배열에 추가 
 * 
 * 코사라주 알고리즘 구현
 * 1. 아직 방문하지 않은 모든 정점에 대하여 순방향 그래프를 이용하여 DFS를 수행
 * 2. 탐색이 끝난 노드부터 스택에 PUSH해줌
 * 3. 스택으로부터 스택이 완전히 빌때까지 하나씩 POP하면서 방문하지 않은 정점에 대하여 역방향 그래프를 이용하여 DFS 수행
 * 4. 이 과정에서 탐색되는 모든 노드는 하나의 SCC안에 포함이 됨
 *
 */
public class BOJ_2152_여행계획세우기 {

	static BufferedReader br;
	static StringTokenizer st;
	
	static int vertexCount, edgeCount;
	static int startVertex, endVertex;
	static ArrayList<ArrayList<Integer>> graph, sccGraph; // 원본 그래프
	static ArrayList<Integer>[] sccList; // SCC 그룹 리스트
	static int[] discovered, sccId; // discovered: 각 정점 발견 순서, sccId: 각 정점 컴포넌트 번호
	static int count, groupNumber; // count: 노드마다 고유한 SCC 번호, groupNumber: SCC 그룹의 고유한 SCC번호
	static Stack<Integer> stackedVertexes; // 방문하면서 쌓인 vertex들
	static int[] sccDistance;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		init();
		
		for (int vertexIndex = 0; vertexIndex < vertexCount; vertexIndex++) {
			if (sccId[vertexIndex] == -1) {
				findSCC(vertexIndex);			
			}
		}
		
		// 같은 그룹 내에 있는 경우 => 그룹의 크기 => SCC 집합은 DAG기 때문에 순환할 수 없다.
		if (sccId[startVertex] == sccId[endVertex]) {
			System.out.print(sccList[sccId[startVertex]].size());
			return;
		}
		
		// 절대 갈 수 없는 경우
		// 도착 해야하는 곳 SCC ID가 시작하는 곳보다 큰 경우
		if (sccId[startVertex] < sccId[endVertex]) {
			System.out.print(0);
			return;
		}
		
		// 다른 그룹에 있는 경우 => 시작 그룹 -> 끝나는 그룹으로 최장거리 탐색 필요
		buildSccGraph();
		
		visited = new boolean[groupNumber];
		sccDistance = new int[groupNumber];
		sccDistance[sccId[startVertex]] = sccList[sccId[startVertex]].size();
		findMaxDistance(sccId[startVertex]);
		
		System.out.print(sccDistance[sccId[endVertex]]);
	}
	
	static int findSCC(int now) {
		discovered[now] = count++; // n번 째로 방문한 노드라고 표시 (시작은  0)
		stackedVertexes.push(now); // 방문한 노드는 스택에 추가
		
		int discoveredParent = discovered[now]; // DFS탐색을 하면서 발견한 제일 작은 노드의 값 
		for (int index = 0; index < graph.get(now).size(); index++) {
			int next = graph.get(now).get(index); // 다음 노드 탐색
			// 만약 아직 방문하지 않은 노드라면
			if (discovered[next] == -1) {
				discoveredParent = Math.min(discoveredParent, findSCC(next));
			}
			// 방문한 상태이지만 아직 구성된 scc가 없는 경우
			// 같은 SCC에 속하는 노드들은 같은 부모를 갖는다고 보고, 그 SCC에 속한 노드들 중에서 가장 id값이 작은 것을 부모로 지정
			else if (sccId[next] == -1) {
				discoveredParent = Math.min(discoveredParent, discovered[next]);
			}
		}
		
		// 부모 노드의 DFS가 끝난 경우
		// 자신의 id값이 스택에서 나올 때까지 스택에 있는 노드들을 pop하고 SCC배열에 추가
		if (discoveredParent == discovered[now]) {
			ArrayList<Integer> scc = new ArrayList<>();
			while (true) {
				int component = stackedVertexes.pop();
				scc.add(component);
				sccId[component] = groupNumber;
				if (component == now) {
					break;
				}
			}
			
			sccList[groupNumber] = scc;
			groupNumber++;
		}
		
		return discoveredParent;
	}
	
	// SCC 그래프 생성
	static void buildSccGraph() {
	    sccGraph = new ArrayList<>();
	    for (int groupIndex = 0; groupIndex < groupNumber; groupIndex++) {
	        sccGraph.add(new ArrayList<>());
	    }

	    for (int now = 0; now < vertexCount; now++) {
	        for (int next : graph.get(now)) {
	            int nowScc = sccId[now];
	            int nextScc = sccId[next];

	            if (nowScc != nextScc) {
	                sccGraph.get(nowScc).add(nextScc);
	            }
	        }
	    }
	    
	    // 중복 제거
	    for (int sccIndex = 0; sccIndex < groupNumber; sccIndex++) {
	        HashSet<Integer> set = new HashSet<>(sccGraph.get(sccIndex));
	        sccGraph.get(sccIndex).clear();  // 기존 리스트 비우기
	        sccGraph.get(sccIndex).addAll(set);  // HashSet을 리스트로 변환하여 추가
	    }
	}
	
	// SCC 그래프 최장거리 탐색
	static void findMaxDistance(int currentSccIndex) {
        for (int nextSccIndex : sccGraph.get(currentSccIndex)) {
        	int nextDistance = sccDistance[currentSccIndex] + sccList[nextSccIndex].size();
        	if (nextDistance > sccDistance[nextSccIndex]) {
        		sccDistance[nextSccIndex] = nextDistance;
        		findMaxDistance(nextSccIndex);
        	}
        }
	}
	
	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		vertexCount = Integer.parseInt(st.nextToken());
		edgeCount = Integer.parseInt(st.nextToken());
		startVertex = Integer.parseInt(st.nextToken()) - 1;
		endVertex = Integer.parseInt(st.nextToken()) - 1;
		
		graph = new ArrayList<>();
		for (int vertexIndex = 0; vertexIndex < vertexCount; vertexIndex++) {
			graph.add(new ArrayList<>());
		}
		
		for (int edgeIndex = 0; edgeIndex < edgeCount; edgeIndex++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			graph.get(start).add(end);
		}
		
		stackedVertexes = new Stack<>();
		sccList = new ArrayList[vertexCount];
		
		discovered = new int[vertexCount];
		Arrays.fill(discovered, -1);
		sccId = new int[vertexCount];
		Arrays.fill(sccId, -1);
	}

}
