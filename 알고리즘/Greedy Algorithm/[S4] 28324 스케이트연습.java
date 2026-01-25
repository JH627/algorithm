import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_28324_스케이트연습 {

	static BufferedReader br;
	static StringTokenizer st;

	static int pointCount;
	static int[] limit;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getMaxResult());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		pointCount = Integer.parseInt(br.readLine());
		limit = new int[pointCount];

		st = new StringTokenizer(br.readLine());
		for (int point = 0; point < pointCount; point++) {
			limit[point] = Integer.parseInt(st.nextToken());
		}
	}

	static long getMaxResult() {
		long result = 1;

		int prev = 1;
		for (int point = pointCount - 2; point >= 0; point--) {
			prev = (prev < limit[point]) ? prev + 1 : limit[point];
			result += prev;
		}

		return result;
	}
}
