import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class BOJ_2957_이진탐색트리 {

	static BufferedReader br;
	static StringBuilder sb;

	static int numberCount;
	static TreeSet<Integer> set;
	static int[] depth;

	public static void main(String[] args) throws IOException {
		init();
		insertNumbers();
		System.out.print(sb);
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		numberCount = Integer.parseInt(br.readLine());

		set = new TreeSet<>();
		set.add(0);
		set.add(numberCount + 1);

		depth = new int[numberCount + 2];
		depth[0] = -1;
		depth[numberCount + 1] = -1;
	}

	static void insertNumbers() throws IOException {
		long count = 0;

		for (int number = 0; number < numberCount; number++) {
			int num = Integer.parseInt(br.readLine());

			int prevDepth = depth[set.lower(num)];
			int nextDepth = depth[set.higher(num)];

			depth[num] = Math.max(nextDepth, prevDepth) + 1;
			set.add(num);
			count += depth[num];

			sb.append(count).append("\n");
		}
	}
}
