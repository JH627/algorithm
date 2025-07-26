import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9507_GenerationsofTribbles {

	static BufferedReader br;
	static StringBuilder sb;

	static long[] fib;

	public static void main(String[] args) throws IOException {
		init();
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 0; testCase < TC; testCase++) {
			sb.append(fib[Integer.parseInt(br.readLine())]).append("\n");
		}
		System.out.print(sb);
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		fib = new long[68];
		fib[0] = 1;
		fib[1] = 1;
		fib[2] = 2;
		fib[3] = 4;
		for (int num = 4; num < 68; num++) {
			fib[num] = fib[num - 1] + fib[num - 2] + fib[num - 3] + fib[num - 4];
		}
	}
}
