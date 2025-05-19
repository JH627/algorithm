import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_5567_결혼식 {

	static BufferedReader br;
	static StringTokenizer st;

	static int vertexCount, edgeCount;

	static ArrayList<ArrayList<Integer>> graph;

	public static void main(String[] args) throws IOException {
		init();

		System.out.print(getFriendCount());
	}

	static int getFriendCount() {
		boolean[] visited = new boolean[vertexCount];
		Queue<int[]> toVisit = new LinkedList<>();

		int count = -1;
		toVisit.add(new int[]{0, 0});
		visited[0] = true;
		while (!toVisit.isEmpty()) {
			int[] now = toVisit.poll();
			count++;

			for (int neighbor : graph.get(now[0])) {
				if (!visited[neighbor]) {
					visited[neighbor] = true;
					if (now[1] == 1) {
						count++;
					}
					else {
						toVisit.add(new int[]{neighbor, 1});
					}
				}
			}
		}

		return count;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		vertexCount = Integer.parseInt(br.readLine());
		edgeCount = Integer.parseInt(br.readLine());

		graph = new ArrayList<>();
		for (int vertexIndex = 0; vertexIndex < vertexCount; vertexIndex++) {
			graph.add(new ArrayList<>());
		}

		for (int edgeIndex = 0; edgeIndex < edgeCount; edgeIndex++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
	}

}
