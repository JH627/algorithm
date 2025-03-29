import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1120_문자열 {

	static BufferedReader br;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());

		String a = st.nextToken();
		String b = st.nextToken();

		int aLen = a.length();
		int bLen = b.length();

		int min = bLen;
		for (int startIndex = 0; startIndex <= bLen - aLen; startIndex++) {
			int diff = 0;
			for (int aIndex = 0; aIndex < aLen; aIndex++) {
				if (a.charAt(aIndex) != b.charAt(startIndex + aIndex)) {
					diff++;
				}
			}

			if (diff == 0) {
				System.out.print(0);
				return;
			}

			min = Math.min(min, diff);
		}

		System.out.print(min);
	}
}
