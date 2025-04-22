import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1254_팰린드롬만들기 {

	static BufferedReader br;

	static char[] str;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine().toCharArray();

		int len = str.length;
		int result = len;
		for (int index = 0; index < len; index++) {
			if (isPalindrome(index, len - 1)) {
				result = index * 2 + (len - index);
				break;
			}
		}

		System.out.print(result);
	}

	static boolean isPalindrome(int start, int end) {
		while (start < end) {
			if (str[start] != str[end]) {
				return false;
			}
			start++;
			end--;
		}
		return true;
	}
}
