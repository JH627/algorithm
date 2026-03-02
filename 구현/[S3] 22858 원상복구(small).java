import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_22858_원상복구(small) {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int cardCount, shuffleCount;
	static int[] result, rule;
	static int[] reverse;

	public static void main(String[] args) throws IOException {
		init();
		getOriginal();
		printResult();
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		cardCount = Integer.parseInt(st.nextToken());
		shuffleCount = Integer.parseInt(st.nextToken());

		result = new int[cardCount + 1];
		st = new StringTokenizer(br.readLine());
		for (int card = 1; card < cardCount + 1; card++) {
			result[card] = Integer.parseInt(st.nextToken());
		}

		rule = new int[cardCount + 1];
		st = new StringTokenizer(br.readLine());
		for (int card = 1; card < cardCount + 1; card++) {
			rule[card] = Integer.parseInt(st.nextToken());
		}

		reverse = new int[cardCount + 1];
		for (int card = 1; card <= cardCount; card++) {
			reverse[rule[card]] = card;
		}
	}

	static void getOriginal() {
		int[] cur = result;
		for (int shuffle = 0; shuffle < shuffleCount; shuffle++) {
			int[] prev = new int[cardCount + 1];
			for (int pos = 1; pos < cardCount + 1; pos++) {
				prev[pos] = cur[reverse[pos]];
			}
			cur = prev;
		}
		result = cur;
	}

	static void printResult() {
		sb = new StringBuilder();
		for (int card = 1; card < cardCount + 1; card++) {
			sb.append(result[card]).append(" ");
		}
		System.out.print(sb);
	}
}
