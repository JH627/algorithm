import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_7578_공장 {

	static BufferedReader br;
	static StringTokenizer st;

	static int elementCount;
	static long[] segTree;
	static HashMap<Integer, Integer> map;

	public static void main(String[] args) throws IOException {
		init();

		System.out.print(getSwapCount());
	}

	static long getSwapCount() throws IOException {
		long swapCount = 0;

		st = new StringTokenizer(br.readLine());
		for (int index = 0; index < elementCount; index++) {
			Integer key = Integer.parseInt(st.nextToken());
			int value = map.get(key);

			updateQuery(1, elementCount, 1, value);
			swapCount += getQuery(1, elementCount, 1, value + 1, elementCount);
		}

		return swapCount;
	}

	static void updateQuery(int start, int end, int node, int target) {
		if (start == end) {
			segTree[node]++;
			return;
		}
		segTree[node]++;

		int mid = (start + end) / 2;
		if (target <= mid) {
			updateQuery(start, mid, node * 2, target);
		}
		else {
			updateQuery(mid + 1, end, node * 2 + 1, target);
		}
	}

	static long getQuery(int start, int end, int node, int left, int right) {
		if (left > end || start > right) {
			return 0;
		}

		if (left <= start && end <= right) {
			return segTree[node];
		}

		int mid = (start + end) / 2;
		return getQuery(start, mid, node * 2, left, right)
			+ getQuery(mid + 1, end, node * 2 + 1, left, right);
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		elementCount = Integer.parseInt(br.readLine());
		map = new HashMap<>();

		st = new StringTokenizer(br.readLine());
		for (int top = 0; top < elementCount; top++) {
			map.put(Integer.parseInt(st.nextToken()), top + 1);
		}

		segTree = new long[elementCount * 4];
	}
}
