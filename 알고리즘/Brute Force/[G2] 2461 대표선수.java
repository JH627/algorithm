import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2461_대표선수 {

	static BufferedReader br;
	static StringTokenizer st;

	static int classCount, studentCount;
	static int[][] power;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getMinDiff());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		classCount = Integer.parseInt(st.nextToken());
		studentCount = Integer.parseInt(st.nextToken());

		power = new int[classCount][studentCount];
		for (int classIndex = 0; classIndex < classCount; classIndex++) {
			st = new StringTokenizer(br.readLine());
			for (int studentIndex = 0; studentIndex < studentCount; studentIndex++) {
				power[classIndex][studentIndex] = Integer.parseInt(st.nextToken());
			}
		}
	}

	static int getMinDiff() {
		if (classCount == 1) {
			return 0;
		}

		for (int classIndex = 0; classIndex < classCount; classIndex++) {
			Arrays.sort(power[classIndex]);
		}

		int minDiff = Integer.MAX_VALUE;
		int[] cursor = new int[classCount];
		while (true) {
			int minClass = 0;
			int minPower = power[0][cursor[0]];

			for (int classIndex = 0; classIndex < classCount; classIndex++) {
				int currentCursor = cursor[classIndex];
				if (minPower > power[classIndex][currentCursor]) {
					minPower = power[classIndex][currentCursor];
					minClass = classIndex;
				}
			}

			minDiff = Math.min(minDiff, getDiff(cursor));

			cursor[minClass]++;
			if (cursor[minClass] == studentCount) {
				break;
			}
		}

		return minDiff;
	}

	static int getDiff(int[] cursor) {
		int minPower = Integer.MAX_VALUE;
		int maxPower = Integer.MIN_VALUE;

		for (int classIndex = 0; classIndex < classCount; classIndex++) {
			minPower = Math.min(minPower, power[classIndex][cursor[classIndex]]);
			maxPower = Math.max(maxPower, power[classIndex][cursor[classIndex]]);
		}

		return maxPower - minPower;
	}
}
