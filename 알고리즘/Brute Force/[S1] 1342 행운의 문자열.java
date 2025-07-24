import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1342_행운의문자열 {

	static BufferedReader br;

	static int length;
	static int[] alphaCount;
	static int count;

	public static void main(String[] args) throws IOException {
		init();
		getCase(-1, 0);
		System.out.print(count);
	}

	static void getCase(int prev, int stringLength) {
		if (stringLength == length) {
			count++;
			return;
		}

		for (int alphabet = 0; alphabet < 26; alphabet++) {
			if (alphaCount[alphabet] > 0 && alphabet != prev) {
				alphaCount[alphabet]--;
				getCase(alphabet, stringLength + 1);
				alphaCount[alphabet]++;
			}
		}
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		length = s.length();
		alphaCount = new int[26];
		for (int index = 0; index < length; index++) {
			alphaCount[s.charAt(index) - 'a']++;
		}
	}

}
