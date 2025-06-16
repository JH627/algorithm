import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_1368_물대기 {

	static BufferedReader br;
	static StringTokenizer st;

	static class Edge implements Comparable<Edge> {
		int start, end, cost;

		public Edge(int start, int end, int cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
	}

	static int fieldCount;
	static int[] singleCost;
	static int[] parent;
	static ArrayList<Edge> edges;

	public static void main(String[] args) throws IOException {
		init();
		int connectCost = unionField();
		System.out.print(getMinDigPointCost() + connectCost);
	}

	static int unionField() {
		Collections.sort(edges);
		int connectCost = 0;
		for (Edge edge : edges) {
			connectCost += union(edge);
		}
		return connectCost;
	}

	static int union(Edge edge) {
		int px = find(edge.start);
		int py = find(edge.end);

		if (px == py) {
			return 0;
		}

		int digAllCost = singleCost[px] + singleCost[py];
		int digMinAndConnectCost = Math.min(singleCost[px], singleCost[py]) + edge.cost;
		if (digAllCost < digMinAndConnectCost) {
			return 0;
		}

		if (singleCost[px] < singleCost[py]) {
			parent[py] = px;
		}
		else {
			parent[px] = py;
		}
		return edge.cost;
	}

	static int find(int x) {
		if (parent[x] == x) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}

	static int getMinDigPointCost() {
		boolean[] checked = new boolean[fieldCount];
		int sum = 0;
		for (int field = 0; field < fieldCount; field++) {
			int pField = find(field);
			if (!checked[pField]) {
				checked[pField] = true;
				sum += singleCost[pField];
			}
		}
		return sum;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		fieldCount = Integer.parseInt(br.readLine());

		singleCost = new int[fieldCount];
		for (int field = 0; field < fieldCount; field++) {
			singleCost[field] = Integer.parseInt(br.readLine());
		}

		edges = new ArrayList<>();
		for (int start = 0; start < fieldCount; start++) {
			st = new StringTokenizer(br.readLine());
			for (int end = 0; end < fieldCount; end++) {
				int cost = Integer.parseInt(st.nextToken());
				if (start == end) {
					continue;
				}
				edges.add(new Edge(start, end, cost));
			}
		}

		parent = new int[fieldCount];
		for (int field = 0; field < fieldCount; field++) {
			parent[field] = field;
		}
	}
}
