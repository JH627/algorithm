import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_25193_곰곰이의식단관리 {

	static BufferedReader br;

	static int dayCount;
	static String foods;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getMinDate());
	}

	static int getMinDate() {
		int chickenCount = 0;
		for (int day = 0; day < dayCount; day++) {
			if (foods.charAt(day) == 'C') {
				chickenCount++;
			}
		}
		return (int)Math.ceil((double) chickenCount / (dayCount - chickenCount + 1));
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		dayCount = Integer.parseInt(br.readLine());
		foods = br.readLine();
	}
}
