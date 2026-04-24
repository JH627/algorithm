import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1082_방번호 {

	static BufferedReader br;
	static StringTokenizer st;

	static int numberCount, costLimit;
	static int[] cost;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getMaxNumber());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		numberCount = Integer.parseInt(br.readLine());
		cost = new int[numberCount];
		st = new StringTokenizer(br.readLine());
		for (int number = 0; number < numberCount; number++) {
			cost[number] = Integer.parseInt(st.nextToken());
		}

		costLimit = Integer.parseInt(br.readLine());
	}

	static String getMaxNumber() {
		String[] maxNumber = new String[costLimit + 1];

		for (int money = 0; money < costLimit + 1; money++) {
			maxNumber[money] = "";
		}

		for (int money = 0; money < costLimit + 1; money++) {
			for (int num = 0; num < numberCount; num++) {
				int nextMoney = money + cost[num];
				if (nextMoney > costLimit) {
					continue;
				}

				String candidate;
				if (maxNumber[money].isEmpty()) {
					candidate = String.valueOf(num);
				}
				else {
					if (maxNumber[money].equals("0")) {
						continue;
					}
					candidate = maxNumber[money] + num;
				}

				maxNumber[nextMoney] = better(maxNumber[nextMoney], candidate);
			}
		}

		String answer = "";
		for (int money = 0; money < costLimit + 1; money++) {
			answer = better(answer, maxNumber[money]);
		}

		return answer;
	}

	static String better(String a, String b) {
		if (a.length() != b.length()) {
			return a.length() > b.length() ? a : b;
		}
		return a.compareTo(b) >= 0 ? a : b;
	}
}
