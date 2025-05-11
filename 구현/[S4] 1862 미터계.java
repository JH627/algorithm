import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1862_미터계 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String number = br.readLine();

		int length = number.length();
		int sum = 0;
		for (int index = length - 1; index >= 0; index--) {
			int num = number.charAt(index) - '0';

			if (num > 4) {
				sum += (num - 1) * Math.pow(9, length - index - 1);
			}
			else {
				sum += num * Math.pow(9, length - index - 1);
			}
		}

		System.out.print(sum);
	}

}
