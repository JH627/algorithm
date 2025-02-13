import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, K;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		int[] count = new int[100001];
		int max = 0;

		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int l = 0, r = 0; r < N; r++) {
			arr[r] = Integer.parseInt(st.nextToken());
			
			if (++count[arr[r]] <= K) {
				max = Math.max(max, r - l + 1);
			}
			else {
				while (arr[l] != arr[r]) {
					count[arr[l]]--;
					l++;
				}
				count[arr[l]]--;
				l++;
			}
		}

		System.out.print(max);
	}
}
