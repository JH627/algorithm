import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] sour, bitter;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());

		sour = new int[N];
		bitter = new int[N];

		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			sour[n] = Integer.parseInt(st.nextToken());
			bitter[n] = Integer.parseInt(st.nextToken());
		}

		for (int n = 0; n < N; n++) {
			bt(n + 1, sour[n], bitter[n]);
		}

		System.out.print(min);
	}

	static void bt(int index, int s, int b) {
		if (index == N) {
			min = Math.min(min, Math.abs(s - b));
			return;
		}

		bt(index + 1, s, b);
		bt(index + 1, s * sour[index], b + bitter[index]);
	}
}
