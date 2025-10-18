import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class BOJ_28432_끝말잇기 {

	static BufferedReader br;

	static int roundCount, candCount;
	static Character leftChar, rightChar;
	static HashSet<String> used;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(findCand());
	}

	static String findCand() throws IOException {
		candCount = Integer.parseInt(br.readLine());

		for (int cand = 0; cand < candCount; cand++) {
			String word = br.readLine();
			if (used.contains(word)) {
				continue;
			}
			if (leftChar != null && word.charAt(0) != leftChar) {
				continue;
			}
			if (rightChar != null && word.charAt(word.length() - 1) != rightChar) {
				continue;
			}
			return word;
		}
		return null;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		roundCount = Integer.parseInt(br.readLine());
		used = new HashSet<>();

		boolean found = false;
		String prev = null;
		for (int round = 0; round < roundCount; round++) {
			String word = br.readLine();
			if (word.equals("?")) {
				found = true;
				if (prev != null) {
					leftChar = prev.charAt(prev.length() - 1);
				}
			}
			else {
				if (found && rightChar == null) {
					rightChar = word.charAt(0);
				}
				else {
					prev = word;
				}
				used.add(word);
			}
		}
	}
}
