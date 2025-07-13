import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_11576_BaseConversion {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int a, b;
	static int elementCount;
	static long[] element;

	public static void main(String[] args) throws IOException {
		init();
		baseConversion();
		System.out.print(sb);
	}

	static void baseConversion() {
		long sum = 0;
		for (int elementIndex = elementCount - 1; elementIndex >= 0; elementIndex--) {
			sum += (long)(element[elementIndex] * Math.pow(a, elementCount - 1 - elementIndex));
		}

		ArrayList<Long> list = new ArrayList<>();
		while (sum != 0) {
			list.add(sum % b);
			sum /= b;
		}
		for (int elementIndex = list.size() - 1; elementIndex >= 0; elementIndex--) {
			sb.append(list.get(elementIndex)).append(" ");
		}
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());

		elementCount = Integer.parseInt(br.readLine());
		element = new long[elementCount];
		st = new StringTokenizer(br.readLine());
		for (int elementIndex = 0; elementIndex < elementCount; elementIndex++) {
			element[elementIndex] = Long.parseLong(st.nextToken());
		}
	}

}
