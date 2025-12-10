import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_15903_카드합체놀이 {

	static BufferedReader br;
	static StringTokenizer st;

	static int cardCount, sumCount;
	static PriorityQueue<Long> cards;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getMinScore());
	}

	static long getMinScore() {
		for (int sum = 0; sum < sumCount; sum++) {
			long a = cards.poll();
			long b = cards.poll();

			cards.add(a + b);
			cards.add(a + b);
		}

		long score = 0;
		while (!cards.isEmpty()) {
			score += cards.poll();
		}

		return score;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		cardCount = Integer.parseInt(st.nextToken());
		sumCount = Integer.parseInt(st.nextToken());

		cards = new PriorityQueue<>(cardCount);
		st = new StringTokenizer(br.readLine());
		for (int card = 0; card < cardCount; card++) {
			cards.add(Long.parseLong(st.nextToken()));
		}

	}
}
