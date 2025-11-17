import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_27436_벌집2 {

	static BufferedReader br;

	static long num;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(findCount());
	}

	static long findCount() {
		long l = 1;
		long r = (long) (2 * 1e9);
		while (l < r) {
			long mid = l + (r - l) / 2;

			long n = 3 * mid * (mid - 1) + 1;

			if (n < num) {
				l = mid + 1;
			}
			else {
				r = mid;
			}
		}

		return r;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		num = Long.parseLong(br.readLine());
	}
}
