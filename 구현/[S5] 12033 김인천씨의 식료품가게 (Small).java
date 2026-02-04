import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12033_김인천씨의식료품가게(Small) {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int elementCount;
	static int[] prices;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= TC; testCase++) {
			init();
			sb.append("Case #").append(testCase).append(": ");
			findSalePrice();
			sb.append("\n");
		}

		System.out.print(sb);
	}

	static void init() throws IOException {
		elementCount = Integer.parseInt(br.readLine()) * 2;
		prices = new int[elementCount];

		st = new StringTokenizer(br.readLine());
		for (int element = 0; element < elementCount; element++) {
			prices[element] = Integer.parseInt(st.nextToken());
		}
	}

	static void findSalePrice() {
		for (int index = 0; index < elementCount; index++) {
			if (prices[index] == 0) {
				continue;
			}
			sb.append(prices[index]).append(" ");
			int originalPrice = prices[index] / 3 * 4;
			for (int checkIndex = index + 1; checkIndex < elementCount; checkIndex++) {
				if (prices[checkIndex] == originalPrice) {
					prices[checkIndex] = 0;
					break;
				}
			}
		}
	}

}
