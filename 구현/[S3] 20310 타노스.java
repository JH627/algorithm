import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] arr = br.readLine().toCharArray();

		int zeroCount = 0;
		int oneCount = 0;
		for (char c : arr) {
			if (c == '0') {
				zeroCount++;
			}
			else {
				oneCount++;
			}
		}

		zeroCount /= 2;
		oneCount /= 2;

		for (int i = 0; i < arr.length && oneCount != 0; i++) {
			if (arr[i] == '1') {
				arr[i] = '2';
				oneCount--;
			}
		}

		for (int i = arr.length - 1; i >= 0 && zeroCount != 0; i--) {
			if (arr[i] == '0') {
				arr[i] = '2';
				zeroCount--;
			}
		}

		StringBuilder sb = new StringBuilder();
		for (char c : arr) {
			if (c != '2') {
				sb.append(c);
			}
		}

		System.out.print(sb);
	}
}
