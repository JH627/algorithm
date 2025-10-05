import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12782_비트우정지수 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static String a, b;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 0; testCase < TC; testCase++) {
			init();
			sb.append(findCount()).append("\n");
		}
		System.out.print(sb);
	}

	static int findCount() {
		int zero = 0;
		int one = 0;

		for (int index = 0; index < a.length(); index++) {
			if (a.charAt(index) != b.charAt(index)) {
				if (a.charAt(index) == '0') {
					zero++;
				}
				else {
					one++;
				}
			}
		}

		return Math.max(one, zero);
	}

	static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		a = st.nextToken();
		b = st.nextToken();
	}
}
