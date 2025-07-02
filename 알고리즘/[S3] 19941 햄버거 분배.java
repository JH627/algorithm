import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_19941_햄버거분배 {

	static BufferedReader br;
	static StringTokenizer st;

	static String status;
	static int length, handSize;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getMaxManCount());
	}

	static int getMaxManCount() {
		boolean[] eaten = new boolean[length];
		int count = 0;

		for (int index = 0; index < length; index++) {
			if (status.charAt(index) == 'H') {
				int start = Math.max(0, index - handSize);
				int end = Math.min(length - 1, index + handSize);
				for (int j = start; j <= end; j++) {
					if (status.charAt(j) == 'P' && !eaten[j]) {
						eaten[j] = true;
						count++;
						break;
					}
				}
			}
		}

		return count;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		length = Integer.parseInt(st.nextToken());
		handSize = Integer.parseInt(st.nextToken());

		status = br.readLine();
	}
}
