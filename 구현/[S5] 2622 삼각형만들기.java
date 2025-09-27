import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2622_삼각형만들기 {

	static BufferedReader br;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		int length = Integer.parseInt(br.readLine());

		int count = 0;
		for (int a = 1; a <= length / 3; a++) {
			for (int b = a; b <= (length - a) / 2; b++) {
				int c = length - a - b;
				if (b > c) {
					break;
				}
				if (c < a + b) {
					count++;
				}
			}
		}

		System.out.print(count);
	}
}
