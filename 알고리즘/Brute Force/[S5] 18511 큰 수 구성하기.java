import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, K;
	static int[] arr;
	static int ans = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		arr = new int[K];
		st = new StringTokenizer(br.readLine());
		for (int k = 0; k < K; k++) {
			arr[k] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		dfs(0);

		System.out.print(ans);
	}

	static void dfs(int num) {
		if (num > N) {
			return;
		}
		ans = Math.max(ans, num);

		for (int k = K - 1; k >= 0; k--) {
			dfs(num * 10 + arr[k]);
		}
	}
}
