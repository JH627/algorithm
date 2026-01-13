import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_21758_꿀따기 {

	static BufferedReader br;
	static StringTokenizer st;

	static int spotCount;
	static int[] honey;
	static int[] leftSum, rightSum;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(findMaxHoneySpot());
	}

	static int findMaxHoneySpot() {
		int max = Integer.MIN_VALUE;

		for (int spot = 2; spot <= spotCount - 1; spot++) {
			int sum = (leftSum[spotCount] - leftSum[1] - honey[spot]) + (leftSum[spotCount] - leftSum[spot]);
			max = Math.max(max, sum);
		}

		for (int spot = spotCount - 1; spot >= 2; spot--) {
			int sum = (rightSum[1] - rightSum[spotCount] - honey[spot]) + (rightSum[1] - rightSum[spot]);
			max = Math.max(max, sum);
		}

		for (int spot = 2; spot <= spotCount - 1; spot++) {
			int sum = (leftSum[spot] - leftSum[1]) + (rightSum[spot] - rightSum[spotCount]);
			max = Math.max(max, sum);
		}

		return max;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		spotCount = Integer.parseInt(br.readLine());
		honey = new int[spotCount + 1];

		st = new StringTokenizer(br.readLine());
		for (int spot = 1; spot < spotCount + 1; spot++) {
			honey[spot] = Integer.parseInt(st.nextToken());
		}

		leftSum = new int[spotCount + 1];
		rightSum = new int[spotCount + 1];

		for (int spot = 1; spot <= spotCount; spot++) {
			leftSum[spot] = leftSum[spot - 1] + honey[spot];
		}

		rightSum[spotCount] = honey[spotCount];
		for (int spot = spotCount - 1; spot >= 1; spot--) {
			rightSum[spot] = rightSum[spot + 1] + honey[spot];
		}
	}

}
