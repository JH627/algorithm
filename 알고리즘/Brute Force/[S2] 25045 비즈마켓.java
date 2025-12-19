import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_25045_비즈마켓 {

	static BufferedReader br;
	static StringTokenizer st;

	static int itemCount, enterpriseCount;
	static int[] happy, cost;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getMaxMoney());
	}

	static long getMaxMoney() {
		long money = 0;

		int dealCount = Math.min(itemCount, enterpriseCount);

		for (int deal = 0; deal < dealCount; deal++) {
			int happyIndex = itemCount - 1 - deal;
			long diff = (long) happy[happyIndex] - cost[deal];
			if (diff <= 0) {
				break;
			}
			money += diff;
		}

		return money;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		itemCount = Integer.parseInt(st.nextToken());
		enterpriseCount = Integer.parseInt(st.nextToken());

		happy = new int[itemCount];
		st = new StringTokenizer(br.readLine());
		for (int item = 0; item < itemCount; item++) {
			happy[item] = Integer.parseInt(st.nextToken());
		}

		cost = new int[enterpriseCount];
		st = new StringTokenizer(br.readLine());
		for (int enterprise = 0; enterprise < enterpriseCount; enterprise++) {
			cost[enterprise] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(happy);
		Arrays.sort(cost);
	}
}
