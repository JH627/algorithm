import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3060_욕심쟁이돼지 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static final int PIG_COUNT = 6;

	static int dailyMeal;
	static int mealSum;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 0; testCase < TC; testCase++) {
			init();
			sb.append(findMaxDay()).append("\n");
		}

		System.out.print(sb);
	}

	static int findMaxDay() {
		if (mealSum > dailyMeal) {
			return 1;
		}

		return (int) (Math.floor(Math.log((double) dailyMeal / mealSum) / Math.log(4))) + 2;
	}

	static void init() throws IOException {
		dailyMeal = Integer.parseInt(br.readLine());

		mealSum = 0;
		st = new StringTokenizer(br.readLine());
		for (int pig = 0; pig < PIG_COUNT; pig++) {
			mealSum += Integer.parseInt(st.nextToken());
		}
	}
}
