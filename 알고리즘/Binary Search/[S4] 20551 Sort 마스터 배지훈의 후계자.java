import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_20551_Sort마스터배지훈의후계자 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int elementCount, queryCount;
	static int[] element;

	public static void main(String[] args) throws IOException {
		init();
		useQuery();
		System.out.print(sb);
	}

	static void useQuery() throws IOException {
		for (int queryIndex = 0; queryIndex < queryCount; queryIndex++) {
			int target = Integer.parseInt(br.readLine());
			sb.append(findIndex(target)).append("\n");
		}
	}

	static int findIndex(int target) {
		int l = 0;
		int r = elementCount - 1;
		int ret = -1;

		while (l <= r) {
			int m = (l + r) / 2;
			if (element[m] == target) {
				ret = m;
				r = m - 1;
			}
			else if (element[m] < target) {
				l = m + 1;
			}
			else {
				r = m - 1;
			}
		}
		return ret;
	}


	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		elementCount = Integer.parseInt(st.nextToken());
		queryCount = Integer.parseInt(st.nextToken());

		element = new int[elementCount];
		for (int index = 0; index < elementCount; index++) {
			element[index] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(element);
	}
}
