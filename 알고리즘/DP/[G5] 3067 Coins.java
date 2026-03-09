import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3067_Coins {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int coinTypeCount, targetMoney;
	static int[] coinCount;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 0; testCase < TC; testCase++) {
			init();
			sb.append(findCount()).append("\n");
		}

		System.out.print(sb);
	}

	static void init() throws IOException {
		coinTypeCount = Integer.parseInt(br.readLine());

		coinCount = new int[coinTypeCount + 1];
		st = new StringTokenizer(br.readLine());
		for (int type = 1; type <= coinTypeCount; type++) {
			coinCount[type] = Integer.parseInt(st.nextToken());
		}

		targetMoney = Integer.parseInt(br.readLine());
	}

	static int findCount() {
		int[][] count = new int[coinTypeCount + 1][targetMoney + 1];
		
		for (int type = 1; type <= coinTypeCount; type++) {
			count[type][0] = 1;

			for (int money = 1; money <= targetMoney; money++) {
				count[type][money] = count[type - 1][money];
				if (money >= coinCount[type]) {
					count[type][money] += count[type][money - coinCount[type]];
				}
			}
		}

		return count[coinTypeCount][targetMoney];
	}
}
