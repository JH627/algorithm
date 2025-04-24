import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9324_진짜메시지 {

	static BufferedReader br;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 0; testCase < TC; testCase++) {
			String s = br.readLine();

			boolean flag = true;
			int[] count = new int[26];
			for (int index = 0; index < s.length(); index++) {
				int c = s.charAt(index) - 'A';
				count[c]++;
				if (count[c] % 3 == 0) {
					if (index == s.length() - 1) {
						flag = false;
						break;
					}
					if (s.charAt(++index) - 'A' != c) {
						flag = false;
						break;
					}
				}
			}

			sb.append(flag ? "OK\n" : "FAKE\n");
		}

		System.out.println(sb);
	}
}
