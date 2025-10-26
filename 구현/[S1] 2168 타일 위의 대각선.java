import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2168_타일위의대각선 {

	static BufferedReader br;
	static StringTokenizer st;

	static int x, y;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(findBlockCount());
	}

	static int findBlockCount() {
		int gcd = gcd(x, y);
		return x + y - gcd;
	}

	static int gcd(int a, int b) {
		if (b == 0) {
			return a;
		}
		return gcd(b, a % b);
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
	}
}
