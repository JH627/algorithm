import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

public class BOJ_2295_세수의합 {

	static BufferedReader br;

	static int numberCount;
	static int[] numbers;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getKthNumber());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		numberCount = Integer.parseInt(br.readLine());
		numbers = new int[numberCount];
		for (int number = 0; number < numberCount; number++) {
			numbers[number] = Integer.parseInt(br.readLine());
		}
	}

	static int getKthNumber() {
		Arrays.sort(numbers);
		
		HashSet<Integer> set = new HashSet<>();

		for (int i = 0; i < numberCount; i++) {
			for (int j = i; j < numberCount; j++) {
				set.add(numbers[i] + numbers[j]);
			}
		}

		for (int k = numberCount - 1; k >= 0; k--) {
			for (int z = 0; z <= k; z++) {
				if (set.contains(numbers[k] - numbers[z])) {
					return numbers[k];
				}
			}
		}

		return -1;
	}
}
