import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_24499_blobyum {

	static BufferedReader br;
	static StringTokenizer st;

	static int pieCount, eatCount;
	static int[] pies;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getMaxTaste());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		pieCount = Integer.parseInt(st.nextToken());
		eatCount = Integer.parseInt(st.nextToken());

		pies = new int[pieCount];
		st = new StringTokenizer(br.readLine());
		for (int pie = 0; pie < pieCount; pie++) {
			pies[pie] = Integer.parseInt(st.nextToken());
		}
	}

	static int getMaxTaste() {
		int currentTaste = 0;

		for (int pie = 0; pie < eatCount; pie++) {
			currentTaste += pies[pie];
		}

		int maxTaste = currentTaste;

		for (int start = 1; start < pieCount; start++) {
			currentTaste -= pies[start - 1];
			currentTaste += pies[(start + eatCount - 1) % pieCount];
			maxTaste = Math.max(maxTaste, currentTaste);
		}

		return maxTaste;
	}
}
