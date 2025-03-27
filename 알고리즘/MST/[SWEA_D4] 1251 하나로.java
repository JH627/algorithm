import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Kruskal 알고리즘
 *
 * PriorityQueue 사용
 *
 * 1. 정점의 위치를 입력받는다.
 * 2. 모든 섬들의 거리를 PriorityQueue에 저장한다.
 * 3. 거리가 짧은 간선부터 확인한다.
 * 3-1. 사이클을 이루지 않는다면 해당 간선을 추가한다.
 * 3-2. 정점의 개수를 V라고 할 때 V - 1개의 간선을 선택했다면
 *    현재 간선들의 가중치 합을 출력한다. 
 *
 */
public class SWEA_1251_하나로 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static class Edge implements Comparable<Edge> {
		int from, to;
		double weight;
		
		public Edge(int from, int to, double weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		// 거리가 짧은 순으로 정렬
		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.weight, o.weight);
		}
	}
	
	static PriorityQueue<Edge> edgeList;
	static int[] parents, rank;
	static int[] x, y;
		
	static int vertexCount;
	static double tax;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= TC; testCase++) {
			init();
			
			addEdges();
			
			long minLength = makeMST();
			
			sb.append("#").append(testCase).append(" ").append(minLength).append("\n");
		}
		
		System.out.print(sb);
	}
	
	static void addEdges() {
		for (int firstVertex = 0; firstVertex < vertexCount; firstVertex++) {
			int firstX = x[firstVertex];
			int firstY = y[firstVertex];
			for (int secondVertex = firstVertex + 1; secondVertex < vertexCount; secondVertex++) {
				int secondX = x[secondVertex];
				int secondY = y[secondVertex];
				// 거리 계산 E * L * L;
				double dist = tax * (Math.pow(Math.abs(firstX - secondX), 2) + Math.pow(Math.abs(firstY - secondY), 2)); 
				edgeList.add(new Edge(firstVertex, secondVertex, dist));
			}
		}
	}
	
	static long makeMST() {
		int count = 0;
		double sum = 0;
		while (!edgeList.isEmpty()) {
			Edge edge = edgeList.poll();
			if (union(edge.from, edge.to)) {
				sum += edge.weight;
				// V - 1개 의 간선을 고른 경우 종료
				if (++count == vertexCount - 1) {
					break;
				}
			}
		}
		
		return Math.round(sum);
		
	}
	
	static int find(int element) {
		if (element == parents[element]) {
			return element;
		}
		
		return parents[element] = find(parents[element]);
	}
		
	static boolean union(int elementA, int elementB) {
		int aParent = find(elementA);
		int bParent = find(elementB);
		
		if (aParent == bParent) {
			return false;
		}

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
		edgeList = new PriorityQueue<>();
		
		vertexCount = Integer.parseInt(br.readLine());
		x = new int[vertexCount];
		y = new int[vertexCount];
		parents = new int[vertexCount];
		rank = new int[vertexCount];
		
		st = new StringTokenizer(br.readLine());
		for (int vertexIndex = 0; vertexIndex < vertexCount; vertexIndex++) {
			int value = Integer.parseInt(st.nextToken());
			x[vertexIndex] = value;
			parents[vertexIndex] = vertexIndex;
		}
		
		st = new StringTokenizer(br.readLine());
		for (int vertexIndex = 0; vertexIndex < vertexCount; vertexIndex++) {
			int value = Integer.parseInt(st.nextToken());
			y[vertexIndex] = value;
		}
		
		tax = Double.parseDouble(br.readLine());
	}
}
