import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_28419_더하기 {

	static BufferedReader br;
	static StringTokenizer st;

	static int elementCount;
	static int[] elements;
	static long even, odd;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(findMinCount());
	}

	static long findMinCount() {
		if (elementCount == 3 && even > odd) {
			return -1;
		}
		return (even > odd) ? even - odd : odd - even;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		elementCount = Integer.parseInt(br.readLine());

		even = 0; odd = 0;
		st = new StringTokenizer(br.readLine());
		elements = new int[elementCount];
		for (int element = 0; element < elementCount; element++) {
			elements[element] = Integer.parseInt(st.nextToken());
			if (element % 2 == 0) {
				even += elements[element];
			}
			else {
				odd += elements[element];
			}
		}
	}
}
