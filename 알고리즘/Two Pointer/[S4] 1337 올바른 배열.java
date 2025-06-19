import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1337_올바른배열 {

	static BufferedReader br;

	static int numCount;
	static int[] nums;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getMinAddCount());
	}

	static int getMinAddCount() {
		int l = 0;
		int max = 0;
		for (int r = 0; r < numCount; r++) {
			while (nums[r] - nums[l] > 4) {
				l++;
			}

			max = Math.max(max, r - l + 1);
		}

		return 5 - max;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		numCount = Integer.parseInt(br.readLine());
		nums = new int[numCount];
		for (int num = 0; num < numCount; num++) {
			nums[num] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(nums);
	}

}
