import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_16456_하와와 {

	static BufferedReader br;

	static final int MOD = 1000000009;

	static int islandCount;
	static long[] count;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		islandCount = Integer.parseInt(br.readLine());

		count = new long[islandCount + 3];
		count[1] = 1;
		count[2] = 1;
		count[3] = 2;

		for (int i = 4; i < islandCount + 3; i++) {
			count[i] = (count[i - 1] + count[i - 3]) % MOD;

		}

		System.out.print(count[islandCount]);
	}
}
