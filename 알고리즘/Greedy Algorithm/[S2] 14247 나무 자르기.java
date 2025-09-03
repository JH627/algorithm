import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14247_나무자르기 {

	static BufferedReader br;
	static StringTokenizer st;

	static int treeCount;
	static int[] treeHeight;
	static long sum;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getMaxHeight());
	}

	static long getMaxHeight() {
		Arrays.sort(treeHeight);
		for (int tree = 0; tree < treeCount; tree++) {
			sum += treeHeight[tree] * tree;
		}
		return sum;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		treeCount = Integer.parseInt(br.readLine());
		treeHeight = new int[treeCount];
		sum = 0;

		st = new StringTokenizer(br.readLine());
		for (int tree = 0; tree < treeCount; tree++) {
			sum += Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int tree = 0; tree < treeCount; tree++) {
			treeHeight[tree] = Integer.parseInt(st.nextToken());
		}
	}
}
