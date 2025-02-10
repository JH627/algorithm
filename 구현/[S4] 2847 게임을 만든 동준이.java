import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		int ans = 0;
		int[] arr = new int[N];
		for (int n = 0; n < N; n++) {
			arr[n] = Integer.parseInt(br.readLine());
		}

		for (int i = N - 2; i >= 0; i--) {
			if (arr[i] >= arr[i + 1]) {
				int diff = arr[i] - arr[i + 1] + 1;
				ans += diff;
				arr[i] -= diff;
			}
		}
		
		System.out.print(ans);
	}
}
