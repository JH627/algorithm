import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13422_도둑 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int houseCount, stealCount, warningLimit;
	static int[] money;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 0; testCase < TC; testCase++) {
			init();
			sb.append(getCount()).append("\n");
		}

		System.out.print(sb);
	}

	static void init() throws IOException {
		st = new StringTokenizer(br.readLine());

		houseCount = Integer.parseInt(st.nextToken());
		stealCount = Integer.parseInt(st.nextToken());
		warningLimit = Integer.parseInt(st.nextToken());

		money = new int[houseCount];
		st = new StringTokenizer(br.readLine());
		for (int house = 0; house < houseCount; house++) {
			money[house] = Integer.parseInt(st.nextToken());
		}
	}

	static int getCount() {
		if (stealCount == houseCount) {
			int sum = 0;
			for (int house = 0; house < houseCount; house++) {
				sum += money[house];
			}
			return (sum < warningLimit) ? 1 : 0;
		}

		int count = 0;
		int sum = 0;

		for (int house = 0; house < stealCount; house++) {
			sum += money[house];
		}
		if (sum < warningLimit) {
			count++;
		}

		for (int start = 1; start < houseCount; start++) {
			sum -= money[start - 1];
			sum += money[(start + stealCount - 1) % houseCount];

			if (sum < warningLimit) {
				count++;
			}
		}

		return count;
	}
}
