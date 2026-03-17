import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_10384_팬그램 {

	static BufferedReader br;
	static StringBuilder sb;

	static final String NOT_PANGRAM = "Not a pangram";
	static final String PANGRAM = "Pangram!";
	static final String DOUBLE_PANGRAM = "Double pangram!!";
	static final String TRIPLE_PANGRAM = "Triple pangram!!!";

	static String line;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= TC; testCase++) {
			init();
			sb.append("Case ").append(testCase).append(": ").append(getPangramType()).append("\n");
		}

		System.out.print(sb);
	}

	static void init() throws IOException {
		line = br.readLine();
	}

	static String getPangramType() {
		int[] count = new int[26];

		for (int index = 0; index < line.length(); index++) {
			char ch = line.charAt(index);

			if ('a' <= ch && ch <= 'z') {
				count[ch - 'a']++;
			}
			else if ('A' <= ch && ch <= 'Z') {
				count[ch - 'A']++;
			}
		}

		int minCount = Integer.MAX_VALUE;
		for (int index = 0; index < 26; index++) {
			minCount = Math.min(minCount, count[index]);
		}

		switch (minCount) {
			case 0:
				return NOT_PANGRAM;
				case 1:
					return PANGRAM;
					case 2:
						return DOUBLE_PANGRAM;
						case 3:
							return TRIPLE_PANGRAM;
		}

		return NOT_PANGRAM;
	}
}
