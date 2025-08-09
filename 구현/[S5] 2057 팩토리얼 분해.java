import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_2057_팩토리얼분해 {

	static BufferedReader br;

	static long num;

	public static void main(String[] args) throws Exception {
		init();
		System.out.print(find() ? "YES" : "NO");
	}

	static boolean find() {
		if (num == 0) {
			return false;
		}

		long[] arr = new long[21];
		arr[0] = 1;
		for (int i = 1; i < 21; i++) {
			arr[i] = arr[i - 1] * i;
		}

		for (int i = 20; i >= 0; i--) {
			if (num >= arr[i]) {
				num -= arr[i];
			}
		}

		return num == 0;
	}


	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		num = Long.parseLong(br.readLine());
	}
}
