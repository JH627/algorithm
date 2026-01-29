import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1515_수이어쓰기 {

	static BufferedReader br;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		String number = br.readLine();

		int now = 0;
		int index = 0;
		while (index < number.length()) {
			now++;
			char[] nowArray = String.valueOf(now).toCharArray();
			for (char n : nowArray) {
				if (index < number.length() && n == number.charAt(index)) {
					index++;
				}
			}
		}

		System.out.print(now);
	}
}
