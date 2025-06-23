import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1448_삼각형만들기 {

	static BufferedReader br;

	static int numCount;
	static int[] nums;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getMaxTriangle());
	}

	static int getMaxTriangle() {
		for (int num = numCount - 1; num >= 2; num--) {
			if (nums[num] < nums[num - 1] + nums[num - 2]) {
				return nums[num] + nums[num - 1] + nums[num - 2];
			}
		}
		return -1;
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
