import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_9885_Claws {

	static BufferedReader br;
	static StringTokenizer st;

	static class Edge {
		int to, weight;

		Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}

	static int nodeCount, edgeCount;
	static int root;
	static long answer;
	static boolean[] hasParent;
	static long[] subtreeSum;
	static ArrayList<ArrayList<Edge>> tree;

	public static void main(String[] args) throws Exception {
		init();
		calcSubtree(root);
		dfs(root, 0L, 0L);
		System.out.print(answer);
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		nodeCount = Integer.parseInt(br.readLine());
		edgeCount = nodeCount - 1;

		tree = new ArrayList<>();
		for (int node = 0; node < nodeCount; node++) {
			tree.add(new ArrayList<>());
		}

		hasParent = new boolean[nodeCount];
		subtreeSum = new long[nodeCount];

		for (int edge = 0; edge < edgeCount; edge++) {
			st = new StringTokenizer(br.readLine());

			int child = Integer.parseInt(st.nextToken()) - 1;
			int parent = Integer.parseInt(st.nextToken()) - 1;
			int weight = Integer.parseInt(st.nextToken());

			tree.get(parent).add(new Edge(child, weight));
			hasParent[child] = true;
		}

		for (int node = 0; node < nodeCount; node++) {
			if (!hasParent[node]) {
				root = node;
				break;
			}
		}

		answer = 0;
	}

	static long calcSubtree(int now) {
		long sum = 0;

		for (Edge next : tree.get(now)) {
			sum += next.weight + calcSubtree(next.to);
		}

		subtreeSum[now] = sum;
		return sum;
	}

	static void dfs(int now, long pathWeight, long gradeSum) {
		long grade = subtreeSum[now] + pathWeight;
		long nextGradeSum = gradeSum + grade;

		if (tree.get(now).isEmpty()) {
			answer = Math.max(answer, nextGradeSum);
			return;
		}

		for (Edge next : tree.get(now)) {
			dfs(next.to, pathWeight + next.weight, nextGradeSum);
		}
	}
}
