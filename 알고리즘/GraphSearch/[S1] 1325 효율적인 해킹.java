import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1325_효율적인해킹 {

	static BufferedReader br;
	static StringTokenizer st;

	static int vertexCount, edgeCount;
	static ArrayList<ArrayList<Integer>> graph;

	public static void main(String[] args) throws IOException {
		init();

		int[] count = new int[vertexCount];
		int max = 0;
		for (int vertex = 0; vertex < vertexCount; vertex++) {
			count[vertex] = findConnected(vertex);
			max = Math.max(max, count[vertex]);
		}

		StringBuilder sb = new StringBuilder();
		for (int vertex = 0; vertex < vertexCount; vertex++) {
			if (count[vertex] == max) {
				sb.append(vertex + 1).append(" ");
			}
		}
		
		System.out.print(sb);
	}

	static int findConnected(int vertex) {
		boolean[] visited = new boolean[vertexCount];

		int count = 0;
		Queue<Integer> queue = new LinkedList<>();
		queue.add(vertex);
		visited[vertex] = true;
		while (!queue.isEmpty()) {
			int now = queue.poll();

			count++;
			for (int next : graph.get(now)) {
				if (!visited[next]) {
					queue.add(next);
					visited[next] = true;
				}
			}
		}

		return count;
	}

	private static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());

		vertexCount = Integer.parseInt(st.nextToken());
		edgeCount = Integer.parseInt(st.nextToken());
		graph = new ArrayList<>();
		for (int vetexIndex = 0; vetexIndex < vertexCount; vetexIndex++) {
			graph.add(new ArrayList<>());
		}

		for (int edgeIndex = 0; edgeIndex < edgeCount; edgeIndex++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			graph.get(b).add(a);
		}
	}
}
