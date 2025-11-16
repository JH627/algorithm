import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_23561_Young한에너지는부족하다 {

	static BufferedReader br;
	static StringTokenizer st;

	static int elementCount;
	static int[] elements;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getMinDiff());
	}

	static int getMinDiff() {
		Arrays.sort(elements);

		return elements[3 * elementCount - elementCount - 1] - elements[elementCount];
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		elementCount = Integer.parseInt(br.readLine());

		elements = new int[3 * elementCount];
		st = new StringTokenizer(br.readLine());
		for (int element = 0; element < 3 * elementCount; element++) {
			elements[element] = Integer.parseInt(st.nextToken());
		}
	}
}
