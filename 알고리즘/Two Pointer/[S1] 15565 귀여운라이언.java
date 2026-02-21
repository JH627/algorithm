import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15565_귀여운라이언 {

	static BufferedReader br;
	static StringTokenizer st;

	static int dollCount, limit;
	static int[] dolls;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getMinGroupSize());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());

		dollCount = Integer.parseInt(st.nextToken());
		limit = Integer.parseInt(st.nextToken());

		dolls = new int[dollCount];
		st = new StringTokenizer(br.readLine());
		for (int doll = 0; doll < dollCount; doll++) {
			dolls[doll] = Integer.parseInt(st.nextToken());
		}
	}

	static int getMinGroupSize() {
		int l = 0;
		int lionCount = 0;
		int minSize = Integer.MAX_VALUE;

		for (int r = 0; r < dollCount; r++) {
			if (dolls[r] == 1) {
				lionCount++;
			}

			while (lionCount >= limit) {
				minSize = Math.min(minSize, r - l + 1);

				if (dolls[l] == 1) {
					lionCount--;
				}
				l++;
			}
		}

		return (minSize == Integer.MAX_VALUE) ? -1 : minSize;
	}

}
