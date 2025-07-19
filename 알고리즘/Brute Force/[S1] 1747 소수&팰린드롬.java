import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1747_소수&팰린드롬 {

	static BufferedReader br;

	static final int MAX = 2000000;

	static int num;
	static boolean[] prime;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getMinPrime());
	}

	static int getMinPrime() {
		for (int n = num; n <= MAX; n++) {
			if (prime[n] && isPalindrome(n)) {
				return n;
			}
		}
		return -1;
	}

	static boolean isPalindrome(int x) {
		char[] number = String.valueOf(x).toCharArray();
		for (int i = 0; i < number.length / 2; i++) {
			if (number[i] != number[number.length - 1 - i]) {
				return false;
			}
		}
		return true;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		num = Integer.parseInt(br.readLine());
		prime = new boolean[MAX + 1];
		Arrays.fill(prime, true);
		prime[1] = false;
		for (int n = 2; n <= num; n++) {
			if (prime[n]) {
				for (int i = 2; n * i <= MAX; i++) {
					prime[n * i] = false;
				}
			}
		}

	}
}
