import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_30645_인형전시 {

	static BufferedReader br;
	static StringTokenizer st;

	static int rowSize, colSize;
	static int dollCount;
	static int[] height;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getMaxCount());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());

		dollCount = Integer.parseInt(br.readLine());
		height = new int[dollCount];
		st = new StringTokenizer(br.readLine());
		for (int doll = 0; doll < dollCount; doll++) {
			height[doll] = Integer.parseInt(st.nextToken());
		}
	}

	static long getMaxCount() {
		Arrays.sort(height);

		long count = 0;
		int sameCount = 1;

		for (int doll = 1; doll < dollCount; doll++) {
			if (height[doll] == height[doll - 1]) {
				sameCount++;
			}
			else {
				count += Math.min(sameCount, colSize);
				sameCount = 1;
			}
		}

		count += Math.min(sameCount, colSize);

		return Math.min(count, (long) rowSize * colSize);
	}
}
