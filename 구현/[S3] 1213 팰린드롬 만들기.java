import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();

		int[] alphaCount = new int[26];
		int len = s.length();
		for (int i = 0; i < len; i++) {
			alphaCount[s.charAt(i) - 'A']++;
		}

		int solo = -1;
		StringBuilder front = new StringBuilder();
		for (int i = 0; i < 26; i++) {
			front.append(String.valueOf((char)('A' + i)).repeat(Math.max(0, alphaCount[i] / 2)));
			if (alphaCount[i] % 2 != 0) {
				if (solo != -1) {
					System.out.print("I'm Sorry Hansoo");
					return;
				}
				solo = i;
			}
		}
		StringBuilder back = new StringBuilder(front);
		back.reverse();
		if (solo != -1) {
			front.append((char) ('A' + solo));
		}

		System.out.print(front.append(back));
	}
}
