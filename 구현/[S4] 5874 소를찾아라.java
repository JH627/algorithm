import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_5874_소를찾아라 {

	static BufferedReader br;

	static String s;
	static int len;

	public static void main(String[] args) throws Exception {
		init();
		System.out.print(getCount());
	}

	static long getCount() {
		int[] arr = new int[len + 1];
		for (int index = len - 2; index >= 0; index--) {
			arr[index] = arr[index + 1];
			if (s.charAt(index) == ')' && s.charAt(index + 1) == ')') {
				arr[index]++;
			}
		}

		long count = 0;
		for (int index = 0; index + 1 < len; index++) {
			if (s.charAt(index) == '(' && s.charAt(index + 1) == '(') {
				count += arr[index + 1];
			}
		}

		return count;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		s = br.readLine();
		len = s.length();
	}
}
