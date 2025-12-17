import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ_11037_중복없는수 {

	static BufferedReader br;
	static StringBuilder sb;

	static ArrayList<Integer> numbers;
	static boolean[] usedNumber;

	public static void main(String[] args) throws IOException {
		init();

		String line;
		while ((line = br.readLine()) != null) {
			int target = Integer.parseInt(line);
			sb.append(findNextValue(target)).append('\n');
		}

		System.out.print(sb);
	}

	static int findNextValue(int target) {
		int l = 0;
		int r = numbers.size() - 1;

		while (l < r) {
			int m = (l + r) / 2;

			if (numbers.get(m) <= target) {
				l = m + 1;
			}
			else {
				r = m;
			}
		}

		return r + 1 == numbers.size() ? 0 : numbers.get(r);
	}

	static void makeNumber(int currentNumber, int depth) {
		numbers.add(currentNumber);
		if (depth == 9) {
			return;
		}

		for (int num = 1; num <= 9; num++) {
			if (usedNumber[num]) {
				continue;
			}
			usedNumber[num] = true;
			makeNumber(currentNumber * 10 + num, depth + 1);
			usedNumber[num] = false;
		}
	}

	static void init() {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		numbers = new ArrayList<>();
		usedNumber = new boolean[10];

		for (int num = 1; num <= 9; num++) {
			usedNumber[num] = true;
			makeNumber(num, 1);
			usedNumber[num] = false;
		}

		Collections.sort(numbers);
	}
}
