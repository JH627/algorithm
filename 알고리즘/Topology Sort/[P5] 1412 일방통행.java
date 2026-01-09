import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_1412_일방통행 {

	static BufferedReader br;

	static int mapSize;
	static boolean[][] map;

	static ArrayList<ArrayList<Integer>> graph;
	static int[] degree;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(checkCycle() ? "NO" : "YES");
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		mapSize = Integer.parseInt(br.readLine());

		map = new boolean[mapSize][mapSize];
		degree = new int[mapSize];
		graph = new ArrayList<>();
		for (int node = 0; node < mapSize; node++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < mapSize; i++) {
			String line = br.readLine();
			for (int j = 0; j < mapSize; j++) {
				map[i][j] = line.charAt(j) == 'Y';
			}
		}

		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j < mapSize; j++) {
				if (!map[i][j]) {
					continue;
				}
				if (map[i][j] && map[j][i]) {
					continue;
				}
				degree[j]++;
				graph.get(i).add(j);
			}
		}
	}

	static boolean checkCycle() {
		Queue<Integer> nodes = new LinkedList<>();
		for (int node = 0; node < mapSize; node++) {
			if (degree[node] == 0) {
				nodes.add(node);
			}
		}

		while (!nodes.isEmpty()) {
			int now = nodes.poll();

			for (Integer next : graph.get(now)) {
				if (--degree[next] == 0) {
					nodes.add(next);
				}
			}
		}

		boolean hasCycle = false;
		for (int node = 0; node < mapSize; node++) {
			if (degree[node] != 0) {
				hasCycle = true;
				break;
			}
		}

		return hasCycle;
	}
}
