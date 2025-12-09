import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_20115_에너지드링크 {

	static BufferedReader br;
	static StringTokenizer st;

	static int drinkCount;
	static double[] drink;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(findMaxEnergy());
	}

	static double findMaxEnergy() {
		Arrays.sort(drink);

		double max = drink[drinkCount - 1];
		for (int drinkIndex = 0; drinkIndex < drinkCount - 1; drinkIndex++) {
			max += drink[drinkIndex] / 2;
		}

		return max;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		drinkCount = Integer.parseInt(br.readLine());

		drink = new double[drinkCount];
		st = new StringTokenizer(br.readLine());
		for (int drinkIndex = 0; drinkIndex < drinkCount; drinkIndex++) {
			drink[drinkIndex] = Double.parseDouble(st.nextToken());
		}
	}
}
