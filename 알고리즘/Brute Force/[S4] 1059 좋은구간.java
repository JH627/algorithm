import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1059_좋은구간 {

	static BufferedReader br;
	static StringTokenizer st;

	static int elementCount, targetNumber;
	static int[] elements;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getSection());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		elementCount = Integer.parseInt(br.readLine());
		elements = new int[elementCount + 1];
		st = new StringTokenizer(br.readLine());
		for (int index = 1; index <= elementCount; index++) {
			elements[index] = Integer.parseInt(st.nextToken());
		}

		targetNumber = Integer.parseInt(br.readLine());
	}

	static int getSection() {
		Arrays.sort(elements);

		for (int index = 1; index <= elementCount; index++) {
			if (elements[index] == targetNumber) {
				return 0;
			}
			if (elements[index] > targetNumber) {
				return findSectionCount(index);
			}
		}

		return 0;
	}

	static int findSectionCount(int index) {
		int left = targetNumber - elements[index - 1];
		int right = elements[index] - targetNumber;

		return left * right - 1;
	}

}
