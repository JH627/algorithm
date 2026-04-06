import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_14699_관악산등산 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int nodeCount, edgeCount;
	static int[] height, visitNodeCount;
	static ArrayList<ArrayList<Integer>> graph;

	public static void main(String[] args) throws IOException {
		init();
		findNodeCount();
		printNodeCount();
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		nodeCount = Integer.parseInt(st.nextToken());
		edgeCount = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>();
		for (int node = 0; node < nodeCount; node++) {
			graph.add(new ArrayList<>());
		}

		st = new StringTokenizer(br.readLine());
		height = new int[nodeCount];
		for (int node = 0; node < nodeCount; node++) {
			height[node] = Integer.parseInt(st.nextToken());
		}

		for (int edge = 0; edge < edgeCount; edge++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;

			graph.get(a).add(b);
			graph.get(b).add(a);
		}

		visitNodeCount = new int[nodeCount];
	}

	static void findNodeCount() {
		for (int node = 0; node < nodeCount; node++) {
			dfs(node);
		}
	}

	static int dfs(int node) {
		if (visitNodeCount[node] != 0) {
			return visitNodeCount[node];
		}

		visitNodeCount[node] = 1;

		for (int next : graph.get(node)) {
			if (height[next] > height[node]) {
				visitNodeCount[node] = Math.max(visitNodeCount[node], dfs(next) + 1);
			}
		}

		return visitNodeCount[node];
	}

	static void printNodeCount() {
		sb = new StringBuilder();
		for (int node = 0; node < nodeCount; node++) {
			sb.append(visitNodeCount[node]).append("\n");
		}
		System.out.print(sb);
	}

}
