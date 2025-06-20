import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17087_숨바꼭질6 {

	static BufferedReader br;
	static StringTokenizer st;

	static int manCount, curPosition;
	static int[] position;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getGCD());
	}

	static int getGCD() {
		int gcd = position[0];
		for (int pos = 1; pos < manCount; pos++) {
			gcd = getGCD(gcd, position[pos]);
		}
		return gcd;
	}

	static int getGCD(int a, int b) {
		while (b != 0) {
			int temp = a % b;
			a = b;
			b = temp;
		}

		return a;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		manCount = Integer.parseInt(st.nextToken());
		curPosition = Integer.parseInt(st.nextToken());

		position = new int[manCount];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < manCount; i++) {
			int pos = Integer.parseInt(st.nextToken());
			position[i] = Math.abs(curPosition - pos);
		}
	}

}
