import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2661_좋은수열 {

	static BufferedReader br;

	static int targetLength;
	static String answer;

	public static void main(String[] args) throws IOException {
		init();
		findMinNumber("", 0);
		System.out.print(answer);
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		targetLength = Integer.parseInt(br.readLine());
	}

	static void findMinNumber(String currentNumber, int currentLength) {
		if (currentLength == targetLength) {
			answer = currentNumber;
			return;
		}

		for (int next = 1; next <= 3 && answer == null; next++) {
			if (checkNumber(currentNumber + next)) {
				findMinNumber(currentNumber + next, currentLength + 1);
			}
		}
	}

	static boolean checkNumber(String number) {
		int length = number.length();
		for (int index = 1; index <= length / 2; index++) {
			String front = number.substring(length - 2 * index, length - index);
			String back = number.substring(length - index, length);

			if (front.equals(back)) {
				return false;
			}
		}

		return true;
	}
}
