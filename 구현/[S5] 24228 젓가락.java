import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_24228_젓가락 {

	static BufferedReader br;
	static StringTokenizer st;

	static long n, r;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getMaxCount());
	}

	static long getMaxCount() {
		return n + 1 + 2 * (r - 1);
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Long.parseLong(st.nextToken());
		r = Long.parseLong(st.nextToken());
	}
}
