import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static long N, L;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		while (L <= 100) {
			long s = (N / L) - ((L - 1) / 2);
			if (s < 0) {
				break;
			}

			long sum = (s * 2 + L - 1) * L / 2;
			if (sum == N) {
				StringBuilder sb = new StringBuilder();
				for (int l = 0; l < L; l++) {
					sb.append(s + l).append(" ");
				}
				System.out.print(sb);
				return;
			}

			L++;
		}

		System.out.print(-1);
	}
}
