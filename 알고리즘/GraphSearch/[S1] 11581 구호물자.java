import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_11581_구호물자 {

	static BufferedReader br;
	static StringTokenizer st;

	static int crossCount;
	static ArrayList<ArrayList<Integer>> graph;
	static boolean[] visited;
	static boolean hasCycle;

	public static void main(String[] args) throws IOException {
		init();
		checkCycle(0);
		System.out.print(hasCycle ? "CYCLE" : "NO CYCLE");
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		crossCount = Integer.parseInt(br.readLine());
		graph = new ArrayList<>();

		for (int cross = 0; cross < crossCount; cross++) {
			graph.add(new ArrayList<>());
		}

		for (int node = 0; node < crossCount - 1; node++) {
			int edgeCount = Integer.parseInt(br.readLine());

			st = new StringTokenizer(br.readLine());
			for (int edge = 0; edge < edgeCount; edge++) {
				int nextNode = Integer.parseInt(st.nextToken()) - 1;
				graph.get(node).add(nextNode);
			}
		}

		visited = new boolean[crossCount];
		hasCycle = false;
	}

	static void checkCycle(int now) {
		visited[now] = true;

		for (int next : graph.get(now)) {
			if (visited[next]) {
				hasCycle = true;
				return;
			}
			checkCycle(next);
			if (hasCycle) {
				return;
			}
		}

		visited[now] = false;
	}
}
