import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_18429_근손실 {

	static BufferedReader br;
	static StringTokenizer st;

	static final int START_POWER = 500;
	static final int LIMIT_POWER = 500;

	static int dayCount, minusPower;
	static int[] plusPower;

	static boolean[] checked;
	static int count;

	public static void main(String[] args) throws IOException {
		init();
		findCount(START_POWER, 0);
		System.out.print(count);
	}

	static void findCount(int currentPower, int currentDay) {
		if (currentDay == dayCount) {
			count++;
			return;
		}

		for (int day = 0; day < dayCount; day++) {
			int nextPower = currentPower + plusPower[day] - minusPower;

			if (!checked[day] && nextPower >= LIMIT_POWER) {
				checked[day] = true;
				findCount(nextPower, currentDay + 1);
				checked[day] = false;
			}
		}
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
    
		st = new StringTokenizer(br.readLine());
		dayCount = Integer.parseInt(st.nextToken());
		minusPower = Integer.parseInt(st.nextToken());

		plusPower = new int[dayCount];
		st = new StringTokenizer(br.readLine());
		for (int day = 0; day < dayCount; day++) {
			plusPower[day] = Integer.parseInt(st.nextToken());
		}

		checked = new boolean[dayCount];
		count = 0;
	}
}
