import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 오프라인 쿼리 + 유니온 파인드
 * 
 * 1. 역순으로 간선 조립
 * 2. 합할때 다른 집합이었으면 서로의 사이즈를 곱해서 답에 더함
 * 3. 같은 집합이었으면 무시
 *
 */
public class BOJ_17398_통신탑분할 {

	static BufferedReader br;
	static StringTokenizer st;
	
	static class Edge {
		int start, end;
		
		Edge(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	
	static int vertexCount, edgeCount, queryCount, preCount;
	static ArrayList<Edge> tempEdges, edges;
	static int[] parent, size;	
	
	public static void main(String[] args) throws Exception {
		init();
		
		long sum = 0;
		for (int index = 0; index < edgeCount; index++) {
			Edge edge = edges.get(index);
			long cost = union(edge.start, edge.end);
			// 미리 연결된 간선은 제외
			if (index >= preCount) {
				sum += cost;
			}
		}
		
		System.out.print(sum);
	}
	
	
	static int find(int x) {
		if (parent[x] == x) {
			return x;
		}
		
		return parent[x] = find(parent[x]);
	}
	
	static long union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		
		if (pa == pb) {
			return 0;
		}

		int aRank = size[pa];
		int bRank = size[pb];
		
		parent[pb] = pa;
		size[pa] += size[pb];
		
		return aRank * bRank;
	}

	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		vertexCount = Integer.parseInt(st.nextToken());
		edgeCount = Integer.parseInt(st.nextToken());
		queryCount = Integer.parseInt(st.nextToken());
		
		parent = new int[vertexCount];
		for (int index = 0; index < vertexCount; index++) {
			parent[index] = index;
		}
		
		size = new int[vertexCount];
		Arrays.fill(size, 1);
		
		edges = new ArrayList<>();
		tempEdges = new ArrayList<>();
		for (int edgeIndex = 0; edgeIndex < edgeCount; edgeIndex++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken()) - 1;
			tempEdges.add(new Edge(v, w));
		}
		
		preCount = edgeCount;
		boolean[] after = new boolean[edgeCount];
		for (int queryIndex = 0; queryIndex < queryCount; queryIndex++) {
			int index = Integer.parseInt(br.readLine()) - 1;
			after[index] = true;
			edges.add(tempEdges.get(index));
			preCount--;
		}
		
		for (int edgeIndex = 0; edgeIndex < edgeCount; edgeIndex++) {
			if (!after[edgeIndex]) {
				edges.add(tempEdges.get(edgeIndex));
			}
		}
		
		Collections.reverse(edges);
	}
	
}
