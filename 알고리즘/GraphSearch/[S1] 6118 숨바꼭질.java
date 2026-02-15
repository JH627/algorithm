import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_6118_숨바꼭질 {

	static BufferedReader br;
	static StringTokenizer st;

	static int nodeCount, edgeCount;
	static ArrayList<ArrayList<Integer>> graph;

	public static void main(String[] args) throws IOException {
		init();
		findMaxDistance();
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

			graph.get(a).add(b);
			graph.get(b).add(a);
		}
	}

	static void findMaxDistance() {
		int minNodeNumber = 0;
		int maxDistance = -1;
		int maxNodeCount = 0;

		int[] distance = new int[nodeCount + 1];
		Arrays.fill(distance, -1);

		Queue<Integer> toVisit = new LinkedList<>();
		distance[1] = 0;
		toVisit.add(1);

		while (!toVisit.isEmpty()) {
			int now = toVisit.poll();

			if (maxDistance < distance[now]) {
				maxDistance = distance[now];
				maxNodeCount = 1;
				minNodeNumber = now;
			}
			else if (maxDistance == distance[now]) {
				maxNodeCount++;
				minNodeNumber = Math.min(now, minNodeNumber);
			}

			for (int next : graph.get(now)) {
				if (distance[next] >= 0) {
					continue;
				}

				distance[next] = distance[now] + 1;
				toVisit.add(next);
			}
		}

		System.out.printf("%d %d %d", minNodeNumber, maxDistance, maxNodeCount);
	}
}
