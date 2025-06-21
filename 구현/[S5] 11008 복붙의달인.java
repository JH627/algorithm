import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11008_복붙의달인 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static String word, memory;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 0; testCase < TC; testCase++) {
			init();
			sb.append(getCount()).append("\n");
		}
		System.out.print(sb);
	}

	static int getCount() {
		return word.replaceAll(memory, ".").length();
	}

	static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		word = st.nextToken();
		memory = st.nextToken();
	}
}
