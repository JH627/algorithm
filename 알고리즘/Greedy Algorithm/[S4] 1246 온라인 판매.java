import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1246_온라인판매 {

	static BufferedReader br;
	static StringTokenizer st;

	static int eggCount, buyerCount;
	static Integer[] price;

	public static void main(String[] args) throws IOException {
		init();
		getMaxPrice();
	}

	static void getMaxPrice() {
		int maxProfit = 0;
		int bestPrice = 0;
		for (int buyerIndex = 0; buyerIndex < buyerCount; buyerIndex++) {
			int buyers = buyerCount - buyerIndex;
			int canSell = Math.min(eggCount, buyers);
			int profit = price[buyerIndex] * canSell;

			if (profit > maxProfit) {
				maxProfit = profit;
				bestPrice = price[buyerIndex];
			}
		}

		System.out.printf("%d %d", bestPrice, maxProfit);
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		eggCount = Integer.parseInt(st.nextToken());
		buyerCount = Integer.parseInt(st.nextToken());

		price = new Integer[buyerCount];
		for (int index = 0; index < buyerCount; index++) {
			price[index] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(price);
	}
}
