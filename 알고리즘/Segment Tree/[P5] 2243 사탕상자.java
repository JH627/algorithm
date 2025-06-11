import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2243_사탕상자 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static final int TASTE = 1000001;

	static int queryCount;
	static int[] segTree;

	public static void main(String[] args) throws IOException {
		init();
		useQuery();
		System.out.print(sb);
	}

	static void useQuery() throws IOException {
		for (int query = 0; query < queryCount; query++) {
			st = new StringTokenizer(br.readLine());
			int operation = Integer.parseInt(st.nextToken());

			switch (operation) {
				case 1:
					int sequence = Integer.parseInt(st.nextToken());
					sb.append(remove(1, TASTE, 1, sequence)).append("\n");
					break;
				case 2:
					int target = Integer.parseInt(st.nextToken());
					int value = Integer.parseInt(st.nextToken());
					update(1, TASTE, 1, target, value);
					break;
			}
		}
	}

	static void update(int left, int right, int index, int target, int value) {
		if (target < left || target > right) {
			return;
		}

		segTree[index] += value;
		if (left == right) {
			return;
		}

		int mid = left + (right - left) / 2;
		update(left, mid, index * 2, target, value);
		update(mid + 1, right, index * 2 + 1, target, value);
	}


	static int remove(int left, int right, int index, int sequence) {
		segTree[index] -= 1;
		if (left == right) {
			return left;
		}

		int mid = left + (right - left) / 2;
		if (sequence <= segTree[index * 2]) {
			return remove(left, mid, index * 2, sequence);
		}
		else {
			return remove(mid + 1, right, index * 2 + 1, sequence - segTree[index * 2]);
		}
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		queryCount = Integer.parseInt(br.readLine());
		segTree = new int[4 * TASTE];
	}
}
