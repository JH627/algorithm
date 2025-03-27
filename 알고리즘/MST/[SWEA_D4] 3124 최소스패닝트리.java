import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Kruskal 알고리즘
 * 
 * 1. 간선을 입력받는다.
 * 2. 사이클을 이루지 않는다면 해당 간선을 추가한다.
 * 3. 정점의 개수를 V라고 할 때 V - 1개의 간선을 선택했다면
 *    현재 간선들의 가중치 합을 출력한다. 
 *
 */
public class SWEA_3124_최소스패닝트리 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static class Edge implements Comparable<Edge> {
		int from, to;
		int weight;
		
		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		// 가중치가 낮은 순으로 정렬
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
		
	static Edge[] edgeList;
	static int[] parents, rank;
		
	static int vertexCount, edgeCount;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= TC; testCase++) {
			init();
			
			long minDistance = makeMST();
			
			sb.append("#").append(testCase).append(" ").append(minDistance).append("\n");
		}
		
		System.out.print(sb);
	}
	
	static long makeMST() {
		long sum = 0;
		int count = 0;
		for (Edge edge : edgeList) {
			if (union(edge.from, edge.to)) {
				sum += edge.weight;
				// V - 1개 의 간선을 고른 경우 종료
				if (++count == vertexCount - 1) {
					break;
				}
			}
		}
		
		return sum;
	}
		
	static int find(int element) {
		if (element == parents[element]) {
			return element;
		}
		
		// 경로 압축
		return parents[element] = find(parents[element]);
	}
		
	static boolean union(int elementA, int elementB) {
		int aParent = find(elementA);
		int bParent = find(elementB);
		
		if (aParent == bParent) {
			return false;
		}

		// 랭크가 낮은 set을 큰 set에 붙임
		if (rank[aParent] > rank[bParent]) {
			parents[bParent] = aParent;
		}
		else if (rank[aParent] < rank[bParent]) {
			parents[aParent] = bParent;
		}
		else {
			parents[aParent] = bParent;
			rank[bParent]++;
		}
		
		return true;
	}
	
	static void init() throws Exception {
		st = new StringTokenizer(br.readLine());
		
		vertexCount = Integer.parseInt(st.nextToken());
		edgeCount = Integer.parseInt(st.nextToken());
		
		edgeList = new Edge[edgeCount];
		for (int edgeIndex = 0; edgeIndex < edgeCount; edgeIndex++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int weight = Integer.parseInt(st.nextToken());
			edgeList[edgeIndex] = new Edge(from, to, weight);
		}
		
		// 가중치가 낮은 순으로 정렬
		Arrays.sort(edgeList);

		parents = new int[vertexCount];
		rank = new int[vertexCount];
		for (int index = 0; index < vertexCount; index++) {
			parents[index] = index;
		}
	}
}
