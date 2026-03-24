import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_13417_카드문자열 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int cardCount;
	static Deque<Character> cardSet;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 0; testCase < TC; testCase++) {
			init();
			findWord();
			sb.append(getWord()).append("\n");
		}

		System.out.print(sb);
	}

	static void init() throws IOException {
		cardCount = Integer.parseInt(br.readLine());
		cardSet = new ArrayDeque<>();
	}

	static void findWord() throws IOException {
		st = new StringTokenizer(br.readLine());

		for (int card = 0; card < cardCount; card++) {
			char ch = st.nextToken().charAt(0);

			if (cardSet.isEmpty() || cardSet.peekFirst() >= ch) {
				cardSet.addFirst(ch);
			}
			else {
				cardSet.addLast(ch);
			}
		}
	}

	static String getWord() {
		StringBuilder word = new StringBuilder();

		while (!cardSet.isEmpty()) {
			word.append(cardSet.pollFirst());
		}

		return word.toString();
	}
}
