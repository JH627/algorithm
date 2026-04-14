import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16974_레벨햄버거 {

	static BufferedReader br;
	static StringTokenizer st;

	static int targetLevel;
	static long ateLayerCount;

	static long[] burgerSize, pattyCount;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(countPatty(targetLevel, ateLayerCount));
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		targetLevel = Integer.parseInt(st.nextToken());
		ateLayerCount = Long.parseLong(st.nextToken());

		burgerSize = new long[targetLevel + 1];
		pattyCount = new long[targetLevel + 1];

		burgerSize[0] = 1;
		pattyCount[0] = 1;

		for (int level = 1; level <= targetLevel; level++) {
			burgerSize[level] = burgerSize[level - 1] * 2 + 3;
			pattyCount[level] = pattyCount[level - 1] * 2 + 1;
		}
	}

	static long countPatty(int level, long remainLayer) {
		if (remainLayer <= 0) {
			return 0;
		}
		if (level == 0) {
			return 1;
		}

		if (remainLayer == 1) {
			return 0;
		}
		else if (remainLayer <= 1 + burgerSize[level - 1]) {
			return countPatty(level - 1, remainLayer - 1);
		}
		else if (remainLayer == 2 + burgerSize[level - 1]) {
			return pattyCount[level - 1] + 1;
		}
		else if (remainLayer <= 2 + burgerSize[level - 1] * 2) {
			return pattyCount[level - 1] + 1 + countPatty(level - 1, remainLayer - 2 - burgerSize[level - 1]);
		}

		return pattyCount[level];
	}
}
