import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_17609_회문 {

	static BufferedReader br;
	static StringBuilder sb;

	static char[] word;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 0; testCase < TC; testCase++) {
			word = br.readLine().toCharArray();
			sb.append(isPalindrome(0, word.length - 1, 0)).append("\n");
		}

		System.out.print(sb);
	}

	static int isPalindrome(int l, int r, int type) {
		if (type >= 2) {
			return 2;
		}

		while (l < r) {
			if (word[l] != word[r]) {
				int removeLeft = isPalindrome(l + 1, r, type + 1);
				if (removeLeft == 0) {
					return 0;
				}

				int removeRight = isPalindrome(l, r - 1, type + 1);
				return Math.min(removeLeft, removeRight);
			}
			l++;
			r--;
		}

		return type;
	}
}
