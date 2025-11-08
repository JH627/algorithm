import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_26122_가장긴막대자석 {

	static BufferedReader br;

	static int len;
	static String s;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getMaxLength());
	}

	static int getMaxLength() {
		int maxLen = 0;

		char prev = s.charAt(0);
		int prevLen = 1;
		for (int index = 1; index < len; index++) {
			char now = s.charAt(index);

			if (now == prev) {
				prevLen++;
			}
			else {
				int nextLen = 0;
				int nextIndex = index;
				while (nextIndex < len && s.charAt(nextIndex) == now) {
					nextLen++;
					nextIndex++;
				}

				maxLen = Math.max(maxLen, 2 * Math.min(prevLen, nextLen));

				prev = now;
				prevLen = nextLen;
				index = nextIndex - 1;
			}
		}

		return maxLen;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		len = Integer.parseInt(br.readLine());
		s = br.readLine();
	}
}
