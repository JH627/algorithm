import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_11899_괄호끼워넣기 {

	static BufferedReader br;

	static char[] s;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getCount());
	}

	static int getCount() {
		int[] count = new int[2];
		for (char c : s) {
			if (c == '(') {
				count[0]++;
			} else {
				if (count[0] > 0) {
					count[0]--;
				} else {
					count[1]++;
				}
			}
		}
		return count[0] + count[1];
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		s = br.readLine().toCharArray();
	}
}
