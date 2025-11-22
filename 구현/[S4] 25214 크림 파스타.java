import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_25214_크림파스타 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int n;

	public static void main(String[] args) throws IOException {
		init();
		findMin();
	}

	static void findMin() throws IOException {
		sb = new StringBuilder();

		int min = Integer.MAX_VALUE;
		int ans = 0;

		st = new StringTokenizer(br.readLine());
		while (n-- > 0) {
			int now = Integer.parseInt(st.nextToken());
			if (min > now) {
				min = now;
			}
			else {
				ans = Math.max(ans, now - min);
			}
			sb.append(ans).append(" ");
		}

		System.out.print(sb);
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
	}
}
