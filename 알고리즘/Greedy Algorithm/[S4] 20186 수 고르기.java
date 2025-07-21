import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_20186_수고르기 {

	static BufferedReader br;
	static StringTokenizer st;

	static int elementCount, selectCount;
	static Integer[] elements;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getMaxValue());
	}

	static int getMaxValue() {
		int sum = 0;
		for (int index = 0; index < selectCount; index++) {
			sum += elements[index];
		}
		sum -= ((selectCount - 1) * selectCount) / 2;
		return sum;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		elementCount = Integer.parseInt(st.nextToken());
		selectCount = Integer.parseInt(st.nextToken());
		elements = new Integer[elementCount];

		st = new StringTokenizer(br.readLine());
		for (int elementIndex = 0; elementIndex < elementCount; elementIndex++) {
			elements[elementIndex] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(elements, Collections.reverseOrder());
	}

}
