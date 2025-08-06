import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2992_크면서작은수 {

	static BufferedReader br;

	static char[] num;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(nextPermutation());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		num = br.readLine().toCharArray();
	}

	static String nextPermutation() {
		int index = num.length - 2;
		while (index >= 0 && num[index] >= num[index + 1]) {
			index--;
		}

		if (index < 0) {
			return "0";
		}

		int j = num.length - 1;
		while (num[j] <= num[index]) {
			j--;
		}

		swap(index, j);
		reverse(index + 1, num.length - 1);
		return new String(num);
	}

	static void swap(int i, int j) {
		char temp = num[i];
		num[i] = num[j];
		num[j] = temp;
	}

	static void reverse(int start, int end) {
		while (start < end) {
			swap(start++, end--);
		}
	}
}
