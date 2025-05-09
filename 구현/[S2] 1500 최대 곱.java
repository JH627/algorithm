import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1500_최대곱 {

	static BufferedReader br;
	static StringTokenizer st;

	static int s, k;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());

		s = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		int q = s / k;
		int r = s % k;

		long result = 1;
		for (int i = 0; i < r; i++) {
			result *= q + 1;
		}
		for (int i = 0; i < k - r; i++) {
			result *= q;
		}

		System.out.print(result);
	}

}
