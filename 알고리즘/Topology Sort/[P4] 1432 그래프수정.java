import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BOJ_1432_그래프수정 {

	static BufferedReader br;
	static StringBuilder sb;

	static int nodeCount;
	static ArrayList<ArrayList<Integer>> graph;
	static int[] degree;
	static int[] updatedNodeNumbers;

	public static void main(String[] args) throws IOException {
		init();
		int updatedNodeCount = updateNodeNumber();
		printUpdatedNodeNumbers(updatedNodeCount);
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		nodeCount = Integer.parseInt(br.readLine());

		graph = new ArrayList<>();
		for (int node = 0; node < nodeCount; node++) {
			graph.add(new ArrayList<>());
		}

		degree = new int[nodeCount];
		for (int start = 0; start < nodeCount; start++) {
			String line = br.readLine();
			for (int end = 0; end < nodeCount; end++) {
				if (line.charAt(end) == '1') {
					graph.get(end).add(start);
					degree[start]++;
				}
			}
		}

		updatedNodeNumbers = new int[nodeCount];
	}

	static int updateNodeNumber() {
		int updatedNodeNumber = 0;

		PriorityQueue<Integer> nodes = new PriorityQueue<>(Comparator.reverseOrder());
		for (int node = 0; node < nodeCount; node++) {
			if (degree[node] == 0) {
				nodes.add(node);
			}
		}

		int nodeNumber = 0;
		while (!nodes.isEmpty()) {
			int now = nodes.poll();

			updatedNodeNumber++;
			updatedNodeNumbers[now] = nodeNumber++;

			for (int next : graph.get(now)) {
				if (--degree[next] == 0) {
					nodes.add(next);
				}
			}
		}

		return updatedNodeNumber;
	}

	static void printUpdatedNodeNumbers(int updatedNodeCount) {
		if (updatedNodeCount < nodeCount) {
			System.out.print(-1);
			return;
		}

		sb = new StringBuilder();
		for (int node = 0; node < nodeCount; node++) {
			sb.append(nodeCount - updatedNodeNumbers[node]).append(" ");
		}
		System.out.print(sb);
	}
}
