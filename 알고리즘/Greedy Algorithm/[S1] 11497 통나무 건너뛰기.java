import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11497_통나무건너뛰기 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int elementCount;
	static int[] elements;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 0; testCase < TC; testCase++) {
			init();
			sb.append(getMinDiff()).append("\n");
		}
		System.out.print(sb);
	}

	static int getMinDiff() {
		int max = 0;
		int before = elements[0];
		for (int index = 2; index < elementCount; index += 2) {
			max = Math.max(max, Math.abs(elements[index] - before));
			before = elements[index];
		}
		for (int index = (elementCount % 2 == 0) ? elementCount - 1 : elementCount - 2; index >= 0; index -= 2) {
			max = Math.max(max, Math.abs(elements[index] - before));
			before = elements[index];
		}
		return max;
	}

	static void init() throws IOException {
		elementCount = Integer.parseInt(br.readLine());
		elements = new int[elementCount];
		st = new StringTokenizer(br.readLine());
		for (int element = 0; element < elementCount; element++) {
			elements[element] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(elements);
	}

}
