import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14400_편의점2 {

	static BufferedReader br;
	static StringTokenizer st;

	static int customerCount;
	static int[] x, y;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getMinDistance());
	}

	static long getMinDistance() {
		int midX = x[customerCount / 2];
		int midY = y[customerCount / 2];

		long dist = 0;
		for (int customer = 0; customer < customerCount; customer++) {
			dist += Math.abs(x[customer] - midX);
			dist += Math.abs(y[customer] - midY);
		}
		return dist;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		customerCount = Integer.parseInt(br.readLine());
		x = new int[customerCount];
		y = new int[customerCount];

		for (int customer = 0; customer < customerCount; customer++) {
			st = new StringTokenizer(br.readLine());
			int px = Integer.parseInt(st.nextToken());
			int py = Integer.parseInt(st.nextToken());
			x[customer] = px;
			y[customer] = py;
		}

		Arrays.sort(x);
		Arrays.sort(y);
	}

}
