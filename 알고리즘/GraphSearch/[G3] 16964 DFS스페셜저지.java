import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_16964_DFS스페셜저지 {

	static BufferedReader br;
	static StringTokenizer st;

	static int nodeCount;
	static ArrayList<HashSet<Integer>> edges;
	static int[] order;
	static int orderIndex;

	public static void main(String[] args) throws IOException {
		init();
		checkOrder(1);
		System.out.print(orderIndex == nodeCount ? 1 : 0);
	}

	static void checkOrder(int now) {
		if (++orderIndex == nodeCount) {
			return;
		}

		while (true) {
			int next = order[orderIndex];

			if (edges.get(now).contains(next)) {
				checkOrder(next);
			}
			else {
				return;
			}

			if (orderIndex == nodeCount) {
				return;
			}
		}
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		nodeCount = Integer.parseInt(br.readLine());

		edges = new ArrayList<>();
		for (int node = 0; node <= nodeCount; node++) {
			edges.add(new HashSet<>());
		}

		for (int edge = 0; edge < nodeCount - 1; edge++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			edges.get(a).add(b);
			edges.get(b).add(a);
		}

		order = new int[nodeCount];
		st = new StringTokenizer(br.readLine());
		for (int node = 0; node < nodeCount; node++) {
			order[node] = Integer.parseInt(st.nextToken());
		}

		orderIndex = 0;
	}
}
