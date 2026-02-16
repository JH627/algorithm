import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14225_부분수열의합 {

	static BufferedReader br;
	static StringTokenizer st;

	static int numberCount, numberSum;
	static int[] numbers;
	static boolean[] checked;

	public static void main(String[] args) throws IOException {
		init();
		checkNumbers(0, 0);
		System.out.print(findMinNumber());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		numberCount = Integer.parseInt(br.readLine());
		numberSum = 0;
		numbers = new int[numberCount];

		st = new StringTokenizer(br.readLine());
		for (int number = 0; number < numberCount; number++) {
			numbers[number] = Integer.parseInt(st.nextToken());
			numberSum += numbers[number];
		}

		checked = new boolean[numberSum + 1];
	}

	static void checkNumbers(int currentNumber, int index) {
		checked[currentNumber] = true;

		if (index == numberCount) {
			return;
		}

		checkNumbers(currentNumber + numbers[index], index + 1);
		checkNumbers(currentNumber, index + 1);
	}

	static int findMinNumber() {
		for (int number = 1; number <= numberSum; number++) {
			if (!checked[number]) {
				return number;
			}
		}
		return numberSum + 1;
	}
}
