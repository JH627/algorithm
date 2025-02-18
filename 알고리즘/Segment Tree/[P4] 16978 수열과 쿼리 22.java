import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static long[] segTree, nums;
	static ArrayList<int[]> updateQueries, getQueries;
	static long[] answer;

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		init();

		solve();

		for (long ans : answer) {
			sb.append(ans).append("\n");
		}

		System.out.print(sb);
	}

	static void solve() {
		int updateQueriesSize = updateQueries.size();
		int getQueriesSize = getQueries.size();

		int getQueriesIndex = 0;
		for (int updateIdx = 0; updateIdx < updateQueriesSize; updateIdx++) {
			// 0번째 쿼리는 먼저 쿼리 후 업데이트 해야함
			if (updateIdx != 0) {
				int index = updateQueries.get(updateIdx)[0];
				int value = updateQueries.get(updateIdx)[1];
				long diff = value - nums[index];
				update(1, 1, N, index, diff);
				nums[index] = value;
			}

			while (getQueriesIndex < getQueriesSize && getQueries.get(getQueriesIndex)[1] == updateIdx) {
				int[] query = getQueries.get(getQueriesIndex);
				int l = query[2];
				int r = query[3];
				answer[query[0]] = getQuery(1, 1, N, l, r);
				getQueriesIndex++;
			}
		}
	}

	static void update(int node, int s, int e, int index, long diff) {
		if (index < s || index > e) {
			return;
		}

		if (s == e) {
			segTree[node] += diff;
			return;
		}

		int m = (s + e) / 2;
		update(node * 2, s, m, index, diff);
		update(node * 2 + 1, m + 1, e, index, diff);

		segTree[node] = segTree[node * 2] + segTree[node * 2 + 1];
	}


	static long getQuery(int node, int s, int e, int l, long r) {
		if (l > e || r < s) {
			return 0;
		}

		if (l <= s && r >= e) {
			return segTree[node];
		}

		int m = (s + e) / 2;
		return getQuery(node * 2, s, m, l, r) + getQuery(node * 2 + 1, m + 1, e, l, r);
	}

	static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		nums = new long[N + 1];
		segTree = new long[4 * N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int n = 1; n < N + 1; n++) {
			nums[n] = Long.parseLong(st.nextToken());
			update(1, 1, N, n, nums[n]);
		}

		updateQueries = new ArrayList<>();
		getQueries = new ArrayList<>();

		updateQueries.add(new int[]{0, 0});

		int ansIdx = 0;
		int M = Integer.parseInt(br.readLine());
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int op = Integer.parseInt(st.nextToken());
			if (op == 1) {
				int index = Integer.parseInt(st.nextToken());
				int value = Integer.parseInt(st.nextToken());
				updateQueries.add(new int[] {index, value});
			} else {
				int queryCount = Integer.parseInt(st.nextToken());
				int l = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				getQueries.add(new int[] {ansIdx++, queryCount, l, r});
			}
		}

		answer = new long[ansIdx];

		getQueries.sort(new Comparator<>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		});
	}
}
