import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_13413_오셀로재배치 {

	static BufferedReader br;
	static StringBuilder sb;

	static int len;
	static String a, b;

	public static void main(String[] args) throws IOException {
		sb = new StringBuilder();
		br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 0; testCase < TC; testCase++) {
			init();

			int BW = 0;
			int WB = 0;
			for (int index = 0; index < len; index++) {
				if (a.charAt(index) == 'B' && b.charAt(index) == 'W') {
					BW++;
				}
				if (a.charAt(index) == 'W' && b.charAt(index) == 'B') {
					WB++;
				}
			}

			int max = Math.max(BW, WB);
			int min = Math.min(BW, WB);
			int count = min + (max - min);

			sb.append(count).append("\n");
		}

		System.out.print(sb);
	}

	static void init() throws IOException {
		len = Integer.parseInt(br.readLine());
		a = br.readLine();
		b = br.readLine();
	}
}
