import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16194_카드구매하기2 {

	static BufferedReader br;
	static StringTokenizer st;

	static int packCount;
	static int[] cardCount, minPrice;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(findMinPrice());
	}

	static int findMinPrice() {
		for (int i = 1; i < packCount + 1; i++) {
			for (int j = 1; j <= i; j++) {
				minPrice[i] = Math.min(minPrice[i], minPrice[i - j] + cardCount[j]);
			}
		}

		return minPrice[packCount];
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		packCount = Integer.parseInt(br.readLine());
		cardCount = new int[packCount + 1];
		minPrice = new int[packCount + 1];
		
		st = new StringTokenizer(br.readLine());
		for (int pack = 1; pack < packCount + 1; pack++) {
			cardCount[pack] = Integer.parseInt(st.nextToken());
			minPrice[pack] = Integer.MAX_VALUE;
		}
	}
}
