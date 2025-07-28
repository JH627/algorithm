import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14912_숫자빈도수 {

	static BufferedReader br;
	static StringTokenizer st;

	static int n, d;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getCount());
	}

	static int getCount() {
		int count = 0;
		for (int num = 1; num <= n; num++) {
			String s = String.valueOf(num);
			for (int index = 0; index < s.length(); index++) {
				if (s.charAt(index) - '0' == d) {
					count++;
				}
			}
		}
		return count;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
	}
}
