import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1321_군인 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int groupCount;
	static int[] groupSize, segTree;

	public static void main(String[] args) throws IOException {
		init();
		useQuery();
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		groupCount = Integer.parseInt(br.readLine());
		groupSize = new int[groupCount + 1];
		segTree = new int[groupCount * 4];

		st = new StringTokenizer(br.readLine());
		for (int group = 1; group < groupCount + 1; group++) {
			groupSize[group] = Integer.parseInt(st.nextToken());
		}

		makeTree(1, groupCount, 1);
	}

	static void makeTree(int start, int end, int index) {
		if (start == end) {
			segTree[index] = groupSize[start];
			return;
		}

		int mid = (start + end) / 2;
		makeTree(start, mid, index * 2);
		makeTree(mid + 1, end, index * 2 + 1);

		segTree[index] = segTree[index * 2] + segTree[index * 2 + 1];
	}

	static void useQuery() throws IOException {
		sb = new StringBuilder();

		int queryCount = Integer.parseInt(br.readLine());
		for (int query = 0; query < queryCount; query++) {
			st = new StringTokenizer(br.readLine());

			int operation = Integer.parseInt(st.nextToken());
			switch (operation) {
				case 1:
					int target = Integer.parseInt(st.nextToken());
					int diff = Integer.parseInt(st.nextToken());
					update(1, groupCount, 1, target, diff);
					break;
				case 2:
					int targetNumber = Integer.parseInt(st.nextToken());
					sb.append(query(1, groupCount, 1, targetNumber)).append("\n");
					break;
			}
		}

		System.out.print(sb);
	}

	static void update(int start, int end, int index, int target, int diff) {
		segTree[index] += diff;

		if (start == end) {
			return;
		}

		int mid = (start + end) / 2;
		if (mid >= target) {
			update(start, mid, index * 2, target, diff);
		}
		else {
			update(mid + 1, end, index * 2 + 1, target, diff);
		}
	}

	static int query(int start, int end, int index, int target) {
		if (start == end) {
			return start;
		}

		int mid = (start + end) / 2;
		if (segTree[index * 2] >= target) {
			return query(start, mid, index * 2, target);
		}
		else {
			return query(mid + 1, end, index * 2 + 1, target - segTree[index * 2]);
		}
	}
}
