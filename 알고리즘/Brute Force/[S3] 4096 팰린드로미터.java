import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_4096_팰린드로미터 {

	static BufferedReader br;
	static StringBuilder sb;

	static char[] number;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		while (true) {
			String num = br.readLine();
			if ("0".equals(num)) {
				break;
			}

			number = num.toCharArray();
			int count = 0;

			while (!isPalindrome()) {
				addOne();
				count++;
			}

			sb.append(count).append('\n');
		}

		System.out.print(sb);
	}

	static boolean isPalindrome() {
		int left = 0;
		int right = number.length - 1;

		while (left < right) {
			if (number[left] != number[right]) {
				return false;
			}
			left++;
			right--;
		}
		return true;
	}

	static void addOne() {
		int index = number.length - 1;

		while (index >= 0) {
			if (number[index] < '9') {
				number[index]++;
				return;
			}
			else {
				number[index] = '0';
				index--;
			}
		}
	}
}
