import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_21937_작업 {

	static BufferedReader br;
	static StringTokenizer st;

	static int nodeCount, edgeCount, targetNode;
	static ArrayList<ArrayList<Integer>> graph;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getWorkCount());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		nodeCount = Integer.parseInt(st.nextToken());
		edgeCount = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>();
		for (int node = 0; node < nodeCount + 1; node++) {
			graph.add(new ArrayList<>());
		}

		for (int edge = 0; edge < edgeCount; edge++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			graph.get(b).add(a);
		}

		targetNode = Integer.parseInt(br.readLine());
	}

	static int getWorkCount() {
		int count = 0;

		boolean[] visited = new boolean[nodeCount + 1];
		Queue<Integer> toVisit = new LinkedList<>();
		toVisit.add(targetNode);
		visited[targetNode] = true;
		while (!toVisit.isEmpty()) {
			int now = toVisit.poll();

			for (Integer next : graph.get(now)) {
				if (!visited[next]) {
					visited[next] = true;
					toVisit.add(next);
					count++;
				}
			}
		}

		return count;
	}
}
