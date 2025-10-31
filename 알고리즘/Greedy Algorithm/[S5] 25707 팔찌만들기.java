import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_25707_팔찌만들기 {

	static BufferedReader br;
	static StringTokenizer st;

	static int elementCount;
	static int[] elements;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getSum());
	}

	static int getSum() {
		int sum = 0;
		for (int element = 0; element < elementCount - 1; element++) {
			sum += Math.abs(elements[element + 1] - elements[element]);
		}

		sum += Math.abs(elements[0] - elements[elementCount - 1]);
		return sum;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		elementCount = Integer.parseInt(br.readLine());
		elements = new int[elementCount];

		st = new StringTokenizer(br.readLine());
		for (int element = 0; element < elementCount; element++) {
			elements[element] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(elements);
	}
}
