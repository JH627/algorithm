import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15489_파스칼삼각형 {

	static BufferedReader br;
	static StringTokenizer st;

	static int r, c, w;
	static int[][] num;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getSum());
	}

	static int getSum() {
		for (int i = 2; i <= r + w; i++) {
			for (int j = 1; j <= i; j++) {
				num[i][j] = num[i - 1][j - 1] + num[i - 1][j];
			}
		}

		int sum = 0;
		for (int i = 0; i < w; i++) {
			for (int j = 0; j <= i; j++) {
				sum += num[r + i][c + j];
			}
		}
		return sum;
	}

	private static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());

		num = new int[r + w + 1][r + w + 1];
		num[1][1] = 1;
	}
}
