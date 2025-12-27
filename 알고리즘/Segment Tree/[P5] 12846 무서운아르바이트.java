import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12846_무서운아르바이트 {

	static BufferedReader br;
	static StringTokenizer st;

	static int dayCount;
	static int[] money, minMoney;
	static long maxMoney;

	public static void main(String[] args) throws IOException {
		init();
		findMinMoney(1, dayCount, 1);
		findMaxProfit(1, dayCount);
		System.out.print(maxMoney);
	}

	static void findMinMoney(int start, int end, int node) {
		if (start == end) {
			minMoney[node] = start;
			return;
		}

		int mid = (start + end) / 2;
		findMinMoney(start, mid, node * 2);
		findMinMoney(mid + 1, end, node * 2 + 1);

		if (money[minMoney[node * 2]] <= money[minMoney[node * 2 + 1]]) {
			minMoney[node] = minMoney[node * 2];
		}
		else {
			minMoney[node] = minMoney[node * 2 + 1];
		}
	}

	static void findMaxProfit(int left, int right) {
		if (left > right) {
			return;
		}

		if (left == right) {
			maxMoney = Math.max(minMoney[left], maxMoney);
			return;
		}

		int workingDay = right - left + 1;
		int minMoneyDay = getMinMoneyIndex(1, dayCount, 1, left, right);

		maxMoney = Math.max((long)workingDay * money[minMoneyDay], maxMoney);
		findMaxProfit(left, minMoneyDay - 1);
		findMaxProfit(minMoneyDay + 1, right);
	}

	static int getMinMoneyIndex(int start, int end, int node, int left, int right) {
		if (start > right || end < left) {
			return -1;
		}

		if (left <= start && end <= right) {
			return minMoney[node];
		}

		int mid = (start + end) / 2;
		int l = getMinMoneyIndex(start, mid, node * 2, left, right);
		int r = getMinMoneyIndex(mid + 1, end, node * 2 + 1, left, right);
		if (l == -1 || r == -1) {
			return Math.max(l, r);
		}

		return (money[l] <= money[r]) ? l : r;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		dayCount = Integer.parseInt(br.readLine());

		money = new int[dayCount + 1];
		minMoney = new int[4 * dayCount];

		st = new StringTokenizer(br.readLine());
		for (int day = 1; day < dayCount + 1; day++) {
			money[day] = Integer.parseInt(st.nextToken());
		}

		maxMoney = 0;
	}
}
