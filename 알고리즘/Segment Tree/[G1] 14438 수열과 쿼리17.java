import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14438_수열과쿼리17 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int[] elements, segTree;
	static int elementCount, queryCount;

	public static void main(String[] args) throws IOException {
		init();
		useQuery();
		System.out.print(sb);
	}

	static void useQuery() throws IOException {
		for (int queryIndex = 0; queryIndex < queryCount; queryIndex++) {
			st = new StringTokenizer(br.readLine());
			int operation = Integer.parseInt(st.nextToken());
			switch (operation) {
				case 1:
					int target = Integer.parseInt(st.nextToken());
					int value = Integer.parseInt(st.nextToken());
					updateQuery(1, elementCount, 1, target, value);
					break;
				case 2:
					int left = Integer.parseInt(st.nextToken());
					int right = Integer.parseInt(st.nextToken());
					sb.append(getQuery(1, elementCount, 1, left, right)).append("\n");
					break;
			}
		}
	}

	static void updateQuery(int start, int end, int node, int target, int value) {
		if (start > target || end < target) {
			return;
		}
		if (start == end) {
			segTree[node] = value;
			return;
		}

		int mid = (start + end) / 2;
		updateQuery(start, mid, node * 2, target, value);
		updateQuery(mid + 1, end, node * 2 + 1, target, value);
		segTree[node] = Math.min(segTree[node * 2], segTree[node * 2 + 1]);
	}

	static int getQuery(int start, int end, int node, int left, int right) {
		if (start > right || end < left) {
			return Integer.MAX_VALUE;
		}

		if (left <= start && end <= right) {
			return segTree[node];
		}

		int mid = (start + end) / 2;
		return Math.min(getQuery(start, mid, node * 2, left, right), getQuery(mid + 1, end, node * 2 + 1, left, right));
	}

	static void makeSegTree(int start, int end, int node) {
		if (start == end) {
			segTree[node] = elements[start];
			return;
		}

		int mid = (start + end) / 2;
		makeSegTree(start, mid, node * 2);
		makeSegTree(mid + 1, end, node * 2 + 1);
		segTree[node] = Math.min(segTree[node * 2], segTree[node * 2 + 1]);
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		elementCount = Integer.parseInt(br.readLine());
		elements = new int[elementCount + 1];
		st = new StringTokenizer(br.readLine());
		for (int index = 1; index < elementCount + 1; index++) {
			elements[index] = Integer.parseInt(st.nextToken());
		}
		segTree = new int[elementCount * 4];
		queryCount = Integer.parseInt(br.readLine());

		makeSegTree(1, elementCount, 1);
	}
}
