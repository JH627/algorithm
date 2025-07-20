import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_5671_호텔방번호 {

	static BufferedReader br;
	static StringBuilder sb;

	static int n, m;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		String s;
		while ((s = br.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(s);
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			int count = 0;
			for (int num = n; num <= m; num++) {
				if (check(num)) {
					count++;
				}
			}
			sb.append(count).append("\n");
		}
		System.out.print(sb);
	}

	static boolean check(int num) {
		int[] cnt = new int[10];
		while (num != 0) {
			if (++cnt[num % 10] == 2) {
				return false;
			}
			num /= 10;
		}
		return true;
	}
}
