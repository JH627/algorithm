import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_18115_카드놓기 {

	static BufferedReader br;
	static StringBuilder sb;

	static int cardCount;
	static Deque<Integer> cards;

	public static void main(String[] args) throws IOException {
		init();
		findStatus();
		printOriginalStatus();
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		cardCount = Integer.parseInt(br.readLine());
		cards = new ArrayDeque<>();
	}

	static void findStatus() throws IOException {
		String[] operations = br.readLine().split(" ");

		for (int index = operations.length - 1; index >= 0; index--) {
			int now = operations.length - index;
			switch (operations[index]) {
				case "1":
					cards.addFirst(now);
					break;
				case "2":
					int temp = cards.pollFirst();
					cards.addFirst(now);
					cards.addFirst(temp);
					break;
				case "3":
					cards.addLast(now);
					break;
			}
		}
	}

	static void printOriginalStatus() {
		sb = new StringBuilder();
		for (int card = 0; card < cardCount; card++) {
			sb.append(cards.pollFirst()).append(" ");
		}
		System.out.print(sb);
	}

}
