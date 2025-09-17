import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_7696_반복하지않는수 {

	static BufferedReader br;
	static StringBuilder sb;

	static int[] ans;
	static ArrayList<Integer> sequence;
	static int max;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		sequence = new ArrayList<>();
		max = 0;
		while (true) {
			int num = Integer.parseInt(br.readLine());
			if (num == 0) {
				break;
			}
			max = Math.max(max, num);
			sequence.add(num);
		}

		init();
		for (Integer n : sequence) {
			sb.append(ans[n]).append("\n");
		}
		System.out.print(sb);
	}

	static boolean possible(int num) {
		int mask = 0;

		while (num > 0) {
			int d = num % 10;
			int bit = 1 << d;
			if ((mask & bit) != 0) {
				return false;
			}
			mask |= bit;
			num /= 10;
		}

		return true;
	}


	static void init() {
		ans = new int[max + 2];

		int number = 0;
		for (int ansIndex = 1; ansIndex < max + 2; ansIndex++) {
			while (!possible(++number)) {
			}
			ans[ansIndex] = number;
		}
	}

}
