import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1487_물건팔기 {

	static BufferedReader br;
	static StringTokenizer st;

	static int personCount;
	static int[] maxPrice;
	static int[] deliveryCost;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getResult());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		personCount = Integer.parseInt(br.readLine());
		maxPrice = new int[personCount];
		deliveryCost = new int[personCount];

		for (int person = 0; person < personCount; person++) {
			st = new StringTokenizer(br.readLine());
			maxPrice[person] = Integer.parseInt(st.nextToken());
			deliveryCost[person] = Integer.parseInt(st.nextToken());
		}
	}

	static int getResult() {
		long bestProfit = 0;
		int bestPrice = 0;

		for (int person = 0; person < personCount; person++) {
			int price = maxPrice[person];
			long profit = 0;

			for (int index = 0; index < personCount; index++) {
				if (maxPrice[index] < price) {
					continue;
				}

				int margin = price - deliveryCost[index];
				if (margin > 0) {
					profit += margin;
				}
			}

			if (profit > bestProfit) {
				bestProfit = profit;
				bestPrice = price;
			} 
            else if (profit == bestProfit && profit > 0) {
				bestPrice = Math.min(bestPrice, price);
			}
		}

		if (bestProfit <= 0) {
			return 0;
		}
		return bestPrice;
	}
}
