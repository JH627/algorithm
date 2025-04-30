import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_5214_환승 {

	static BufferedReader br;
	static StringTokenizer st;
	
	static class Edge implements Comparable<Edge> {
		int end, cost;

		public Edge(int end, int cost) {
			this.end = end;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
	}
	
	static int vertexCount, tunnelSize, tunnelCount;
	static ArrayList<ArrayList<Edge>> edges;
	
	public static void main(String[] args) throws Exception {
		init();
		
		System.out.println(findMinDistance());
	}
	
	static int findMinDistance() {
		boolean[] visited = new boolean[vertexCount + tunnelCount];
		PriorityQueue<Edge> toVisit = new PriorityQueue<>();
		
		toVisit.add(new Edge(0, 0));
		while (!toVisit.isEmpty()) {
			Edge now = toVisit.poll();
			
			if (visited[now.end]) {
				continue;
			}
			visited[now.end] = true;
			if (now.end == vertexCount - 1) {
				return now.cost + 1;
			}
			
			for (Edge next : edges.get(now.end)) {
				if (visited[next.end]) {
					continue;
				}
				
				toVisit.add(new Edge(next.end, now.cost + next.cost));
				if (next.end == vertexCount - 1 && next.cost == 0) {
					return now.cost + 1;
				}
			}
			
		}
		
		return -1;
	}
	
	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		vertexCount = Integer.parseInt(st.nextToken());
		tunnelSize = Integer.parseInt(st.nextToken());
		tunnelCount = Integer.parseInt(st.nextToken());
		
		edges = new ArrayList<>();
		for (int vertexIndex = 0; vertexIndex < vertexCount + tunnelCount; vertexIndex++) {
			edges.add(new ArrayList<>());
		}
		
		for (int tunnelIndex = 0; tunnelIndex < tunnelCount; tunnelIndex++) {
			st = new StringTokenizer(br.readLine());
			for (int vertexIndex = 0; vertexIndex < tunnelSize; vertexIndex++) {
				int vertex = Integer.parseInt(st.nextToken()) - 1;
				int mid = vertexCount + tunnelIndex;
				// 정점 -> 중심 (가중치 1)
				edges.get(vertex).add(new Edge(mid, 1));
				// 중심 -> 정점 (가중치 0)
				edges.get(mid).add(new Edge(vertex, 0));
			}
		}
	}
}
