import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, K;
	static int[] count;
	static int max = 0;
	static int base = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		count = new int[N];
		for (int n = 0; n < N; n++) {
			String s = br.readLine();
			int cnt = 0;

			int len = s.length();
			for (int index = 0; index < len; index++) {
				cnt |= (1 << (s.charAt(index) - 'a'));
				base |= (1 << (s.charAt(index) - 'a'));
			}

			count[n] = cnt;
		}

		int bitCount = Integer.bitCount(base);
		if (bitCount <= K) {
			System.out.print(N);
			return;
		}

		find(-1, 0, 0);

		System.out.print(max);
	}

	static void find(int index, int depth, int mask) {
		if (depth == K) {
			int cnt = 0;
			for (int n = 0; n < N; n++) {
				if ((mask | count[n]) == mask) {
					cnt++;
				}
			}
			max = Math.max(cnt, max);
			return;
		}

		for (int idx = index + 1; idx < 26; idx++) {
			if ((base & (1 << idx)) != 0) {
				mask |= (1 << idx);
				find(idx, depth + 1, mask);
				mask &= ~(1 << idx);
			}
		}
	}
}
