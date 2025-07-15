import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17127_벚꽃이정보섬에피어난이유 {

	static BufferedReader br;
	static StringTokenizer st;

	static int elementCount;
	static int[] element;
	static int max;

	public static void main(String[] args) throws IOException {
		init();
		getMaxCase(0, 0, 0);
		System.out.print(max);
	}

	static void getMaxCase(int index, int groupCount, int sum) {
		if (groupCount == 4) {
			if (index == elementCount) {
				max = Math.max(max, sum);
			}
			return;
		}

		if (elementCount - index < 4 - groupCount) {
			return;
		}

		int mul = 1;
		for (int idx = index; idx <= elementCount - (4 - groupCount); idx++) {
			mul *= element[idx];
			getMaxCase(idx + 1, groupCount + 1, sum + mul);
		}
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		elementCount = Integer.parseInt(br.readLine());
		element = new int[elementCount];
		st = new StringTokenizer(br.readLine());
		for (int index = 0; index < elementCount; index++) {
			element[index] = Integer.parseInt(st.nextToken());
		}
		max = 0;
	}
}
