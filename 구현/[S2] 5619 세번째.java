import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BOJ_5619_세번째 {

	static BufferedReader br;

	static int elementCount;
	static int[] elements;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(findValue());
	}

	static int findValue() {
		Arrays.sort(elements);


		ArrayList<Integer> result = new ArrayList<>();
		int n = -1;
		for (int i = 0; i < elementCount; i++) {
			for (int j = i + 1; j < Math.min(elementCount, i + 4); j++) {
				result.add(Integer.parseInt(elements[i] + "" + elements[j]));
				result.add(Integer.parseInt(elements[j] + "" + elements[i]));
			}

			Collections.sort(result);

			if (result.size() > 3) {
				if (n == result.get(2)) {
					break;
				}
				else {
					n = result.get(2);
				}
			}
		}

		return result.get(2);
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		elementCount = Integer.parseInt(br.readLine());
		elements = new int[elementCount];
		for (int element = 0; element < elementCount; element++) {
			elements[element] = Integer.parseInt(br.readLine());
		}
	}
}
