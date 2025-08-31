import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1166_선물 {

	static BufferedReader br;
	static StringTokenizer st;

	static long boxCount;
	static long rowSize, colSize, height;

	public static void main(String[] args) throws IOException {
		init();
		System.out.printf("%.9f", findMaxSize());
	}

	static double findMaxSize() {
		double l = 0;
		double r = Math.min(rowSize, Math.min(colSize, height));
		double m;

		while (l < r) {
			m = (l + r) / 2;

			if ((long)(rowSize / m) * (long)(colSize / m) * (long)(height / m) < boxCount) {
				if (r == m) {
					break;
				}
				r = m;
			}
			else {
				if (l == m) {
					break;
				}
				l = m;
			}
		}

		return l;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());

		boxCount = Long.parseLong(st.nextToken());
		rowSize = Long.parseLong(st.nextToken());
		colSize = Long.parseLong(st.nextToken());
		height = Long.parseLong(st.nextToken());
	}
}
