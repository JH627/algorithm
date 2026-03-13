import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1516_게임개발 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int buildingCount;
	static int[] buildTime, degree;
	static ArrayList<ArrayList<Integer>> graph;
	static int[] minTime;

	public static void main(String[] args) throws IOException {
		init();
		checkMinTime();
		printMinTime();
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		buildingCount = Integer.parseInt(br.readLine());
		buildTime = new int[buildingCount];
		degree = new int[buildingCount];
		graph = new ArrayList<>();
		for (int building = 0; building < buildingCount; building++) {
			graph.add(new ArrayList<>());
		}

		for (int building = 0; building < buildingCount; building++) {
			st = new StringTokenizer(br.readLine());
			buildTime[building] = Integer.parseInt(st.nextToken());

			while (true) {
				int prev = Integer.parseInt(st.nextToken());
				if (prev == -1) {
					break;
				}
				prev -= 1;
				degree[building]++;
				graph.get(prev).add(building);
			}
		}
	}

	static void checkMinTime() {
		minTime = new int[buildingCount];

		Queue<Integer> queue = new LinkedList<>();
		for (int building = 0; building < buildingCount; building++) {
			minTime[building] = buildTime[building];
			if (degree[building] == 0) {
				queue.add(building);
			}
		}

		while (!queue.isEmpty()) {
			int current = queue.poll();

			for (int next : graph.get(current)) {
				minTime[next] = Math.max(minTime[next], minTime[current] + buildTime[next]);
				degree[next]--;

				if (degree[next] == 0) {
					queue.add(next);
				}
			}
		}
	}

	static void printMinTime() {
		sb = new StringBuilder();
		for (int building = 0; building < buildingCount; building++) {
			sb.append(minTime[building]).append("\n");
		}
		System.out.print(sb);
	}
}
