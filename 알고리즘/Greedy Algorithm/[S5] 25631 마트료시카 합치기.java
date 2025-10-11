import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_25631_마트료시카합치기 {

	static BufferedReader br;
	static StringTokenizer st;

	static int elementCount;
	static int[] size;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(findMinCount());
	}

	static int findMinCount() {
		Arrays.sort(size);

		int minCount = 1;
		int prev = 0;
		int count = 0;
		for (int element : size) {
			if (element == prev) {
				minCount = Math.max(++count, minCount);
			}
			else {
				prev = element;
				count = 1;
			}
		}

		return minCount;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		elementCount = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		size = new int[elementCount];
		for (int element = 0; element < elementCount; element++) {
			size[element] = Integer.parseInt(st.nextToken());
		}
	}
}
