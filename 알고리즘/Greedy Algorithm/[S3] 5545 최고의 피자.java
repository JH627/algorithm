import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_5545_최고의피자 {

	static BufferedReader br;
	static StringTokenizer st;

	static int toppingCount;
	static int dowPrice, toppingPrice;
	static int dowCalorie;
	static Integer[] toppingCalories;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(maxMaxPoint());
	}

	static int maxMaxPoint() {
		Arrays.sort(toppingCalories, Collections.reverseOrder());

		int maxPoint = dowCalorie / dowPrice;
		int currentPrice = dowPrice;
		int currentCalorie = dowCalorie;
		for (int toppingCalorie : toppingCalories) {
			int nextPrice = currentPrice + toppingPrice;
			int nextCalorie = currentCalorie + toppingCalorie;
			int nextPoint = nextCalorie / nextPrice;
			if (nextPoint < maxPoint) {
				break;
			}
			maxPoint = nextPoint;
			currentPrice = nextPrice;
			currentCalorie = nextCalorie;
		}

		return maxPoint;
	}

	public static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		toppingCount = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		dowPrice = Integer.parseInt(st.nextToken());
		toppingPrice = Integer.parseInt(st.nextToken());
		dowCalorie = Integer.parseInt(br.readLine());
		toppingCalories = new Integer[toppingCount];
		for (int topping = 0; topping < toppingCount; topping++) {
			toppingCalories[topping] = Integer.parseInt(br.readLine());
		}
	}

}
