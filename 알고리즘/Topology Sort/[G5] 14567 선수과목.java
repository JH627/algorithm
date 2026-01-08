import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14567_선수과목 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int nodeCount, edgeCount;
	static ArrayList<ArrayList<Integer>> graph;
	static int[] inDegree, time;

	public static void main(String[] args) throws IOException {
		init();
		findMinTime();
		printResult();
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

		inDegree = new int[nodeCount];
		for (int edge = 0; edge < edgeCount; edge++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;

			graph.get(a).add(b);
			inDegree[b]++;
		}

		time = new int[nodeCount];
		Arrays.fill(time, Integer.MAX_VALUE);
	}

	static void findMinTime() {
		Queue<Integer> nodes = new LinkedList<>();
		for (int node = 0; node < nodeCount; node++) {
			if (inDegree[node] == 0) {
				nodes.add(node);
				time[node] = 1;
			}
		}

		while (!nodes.isEmpty()) {
			int now = nodes.poll();

			for (int next : graph.get(now)) {
				if (--inDegree[next] == 0) {
					nodes.add(next);
					time[next] = time[now] + 1;
				}
			}
		}
	}

	static void printResult() {
		sb = new StringBuilder();
		for (int node = 0; node < nodeCount; node++) {
			sb.append(time[node]).append(" ");
		}
		System.out.print(sb);
	}
}
