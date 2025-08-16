import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2250_트리의높이와너비 {

	static BufferedReader br;
	static StringTokenizer st;

	static int nodeCount, rootNode;
	static ArrayList<int[]> graph;
	static int[] min, max;

	public static void main(String[] args) throws IOException {
		init();
		findNodePosition(rootNode, 1, 0);
		getMaxWidth();
	}

	static int findNodePosition(int now, int level, int pos) {
		// 전
		int left = graph.get(now)[0];
		if (left != -1) {
			pos = findNodePosition(left, level + 1, pos);
		}

		// 중
		pos++;
		min[level] = Math.min(min[level], pos);
		max[level] = Math.max(max[level], pos);

		// 후
		int right = graph.get(now)[1];
		if (right != -1) {
			pos = findNodePosition(right, level + 1, pos);
		}

		return pos;
	}

	static void getMaxWidth() {
		int maxLevel = 0;
		int maxWidth = 0;

		for (int level = 1; level < nodeCount + 1; level++) {
			if (min[level] == Integer.MAX_VALUE) {
				break;
			}
			int width = max[level] - min[level] + 1;
			if (width > maxWidth) {
				maxWidth = width;
				maxLevel = level;
			}
		}

		System.out.printf("%d %d", maxLevel, maxWidth);
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		nodeCount = Integer.parseInt(br.readLine());

		graph = new ArrayList<>();
		for (int node = 0; node <= nodeCount; node++) {
			graph.add(new int[2]);
		}

		boolean[] notRoot = new boolean[nodeCount + 1];
		for (int node = 1; node <= nodeCount; node++) {
			st = new StringTokenizer(br.readLine());
			int nodeIndex = Integer.parseInt(st.nextToken());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			graph.get(nodeIndex)[0] = left;
			graph.get(nodeIndex)[1] = right;
			if (left != -1) {
				notRoot[left] = true;
			}
			if (right != -1) {
				notRoot[right] = true;
			}
		}

		for (int node = 1; node <= nodeCount; node++) {
			if (!notRoot[node]) {
				rootNode = node;
				break;
			}
		}

		min = new int[nodeCount + 1]; max = new int[nodeCount + 1];
		Arrays.fill(min, Integer.MAX_VALUE);
		Arrays.fill(max, Integer.MIN_VALUE);
	}
}
