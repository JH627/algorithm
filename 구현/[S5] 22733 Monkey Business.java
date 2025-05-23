import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_22733_MonkeyBusiness {

	static BufferedReader br;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		init();

		while (true) {
			int number = Integer.parseInt(br.readLine());
			if (number == 0) {
				break;
			}

			int n = 1;
			while (n * n <= number) {
				sb.append(n * n).append("\n");
				n++;
			}
			sb.append("\n");
		}

		System.out.print(sb);
	}


	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
	}
}
