import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_6543_그래프의싱크 {

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int vertexCount, edgeCount;
	static int[] discovered, sccId;
	static int count, sccGroupNumber;
	
	static Stack<Integer> discoveredVertexes;
	
	static ArrayList<ArrayList<Integer>> graph, sccGraph;
	static ArrayList<Integer>[] sccList;
	static ArrayList<Integer> sinkList;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		while (true) {
			if (!init()) {
				break;
			}
			
			for (int vertexIndex = 0; vertexIndex < vertexCount; vertexIndex++) {
				if (sccId[vertexIndex] == -1) {
					findScc(vertexIndex);
				}
			}
			
			findSinkScc();
			
			ArrayList<Integer> result = new ArrayList<>();
			for (int sinkSccIndex : sinkList) {
				for (int v : sccList[sinkSccIndex]) {
					result.add(v);
				}
			}
			
			Collections.sort(result);
			
			for (int vertexNumber : result) {
				sb.append(vertexNumber + 1).append(" ");					
			}
			sb.append("\n");
		}
		
		
		System.out.print(sb);
	}
	
	static int findScc(int now) {
		discovered[now] = count++;
		discoveredVertexes.add(now);
		
		int minParentDiscovered = discovered[now];
		for (int next : graph.get(now)) {
			if (discovered[next] == -1) {
				minParentDiscovered = Math.min(minParentDiscovered, findScc(next));
			}
			else if (sccId[next] == -1) {
				minParentDiscovered = Math.min(minParentDiscovered, discovered[next]);
			}
		}
		
		if (minParentDiscovered == discovered[now]) {
			ArrayList<Integer> scc = new ArrayList<>();
			while (true) {
				int v = discoveredVertexes.pop();
				scc.add(v);
				sccId[v] = sccGroupNumber;
				if (v == now) {
					break;
				}
			}
			
			sccList[sccGroupNumber] = scc;
			sccGroupNumber++;
		}
		
		return minParentDiscovered;
	}
	
	static void findSinkScc() {
		int[] degree = new int[sccGroupNumber];
		
		for (int now = 0; now < vertexCount; now++) {
			for (int next : graph.get(now)) {
				if (sccId[next] != sccId[now]) {
					degree[sccId[now]]++;
				}
			}
		}
		
		sinkList = new ArrayList<>();
		for (int groupIndex = 0; groupIndex < sccGroupNumber; groupIndex++) {
			if (degree[groupIndex] == 0) {
				sinkList.add(groupIndex);
			}
		}
	}
	
	static boolean init() throws Exception {		
		st = new StringTokenizer(br.readLine());
		vertexCount = Integer.parseInt(st.nextToken());
		if (vertexCount == 0) {
			return false;
		}
		edgeCount = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<>();
		for (int vertexIndex = 0; vertexIndex < vertexCount; vertexIndex++) {
			graph.add(new ArrayList<>());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int edgeIndex = 0; edgeIndex < edgeCount; edgeIndex++) {
			int v = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken()) - 1;
			graph.get(v).add(w);
		}
		
		discovered = new int[vertexCount];
		sccId = new int[vertexCount];
		
		Arrays.fill(discovered, -1);
		Arrays.fill(sccId, -1);	
		
		sinkList = new ArrayList<>();
		
		count = 0;
		sccGroupNumber = 0;
		
		discoveredVertexes = new Stack<>();
		
		sccList = new ArrayList[vertexCount];
		
		return true;
	}
}
