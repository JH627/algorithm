import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1135_뉴스전하기 {

	static BufferedReader br;
	static StringTokenizer st;

	static int nodeCount;
	static ArrayList<ArrayList<Integer>> graph;
	static int[] minTime;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(findMinTime());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		nodeCount = Integer.parseInt(br.readLine());

		graph = new ArrayList<>();
		for (int node = 0; node < nodeCount; node++) {
			graph.add(new ArrayList<>());
		}

		minTime = new int[nodeCount];
		Arrays.fill(minTime, Integer.MAX_VALUE);

		st = new StringTokenizer(br.readLine());
		for (int node = 0; node < nodeCount; node++) {
			if (node == 0) {
				st.nextToken();
				continue;
			}
			int parent = Integer.parseInt(st.nextToken());
			graph.get(parent).add(node);
		}
	}

	static int findMinTime() {
		if (nodeCount == 1) {
			return 0;
		}
		checkMinTime(0);
		return minTime[0] - 1;
	}

	static void checkMinTime(int currentNode) {
		if (graph.get(currentNode).isEmpty()) {
			minTime[currentNode] = 1;
			return;
		}

		int[] timeTable = new int[graph.get(currentNode).size()];
		for (int index = 0; index < graph.get(currentNode).size(); index++) {
			int nextNode = graph.get(currentNode).get(index);
			checkMinTime(nextNode);
			timeTable[index] = minTime[nextNode];
		}

		Arrays.sort(timeTable);
		
		int max = 0;
		for (int index = 0; index < timeTable.length; index++) {
			int time = timeTable[timeTable.length - 1 - index] + index;
			max = Math.max(max, time);
		}

		minTime[currentNode] = max + 1;
	}
}
