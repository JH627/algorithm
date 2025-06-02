import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15658_연산자끼워넣기2 {

	static BufferedReader br;
	static StringTokenizer st;

	static int numCount;
	static int[] number;
	static int[] count;
	static int min, max;

	public static void main(String[] args) throws IOException {
		init();

		getNumber(0, number[0]);

		System.out.printf("%d\n%d", max, min);
	}

	static void getNumber(int index, int sum) {
		if (index == numCount - 1) {
			min = Math.min(min, sum);
			max = Math.max(max, sum);
			return;
		}

		for (int operation = 0; operation < 4; operation++) {
			if (count[operation] > 0) {
				switch (operation) {
					case 0:
						count[operation]--;
						getNumber(index + 1, sum + number[index + 1]);
						count[operation]++;
						break;
					case 1:
						count[operation]--;
						getNumber(index + 1, sum - number[index + 1]);
						count[operation]++;
						break;
					case 2:
						count[operation]--;
						getNumber(index + 1, sum * number[index + 1]);
						count[operation]++;
						break;
					case 3:
						count[operation]--;
						getNumber(index + 1, sum / number[index + 1]);
						count[operation]++;
				}
			}
		}
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		numCount = Integer.parseInt(br.readLine());
		number = new int[numCount];

		st = new StringTokenizer(br.readLine());
		for (int index = 0; index < numCount; index++) {
			number[index] = Integer.parseInt(st.nextToken());
		}

		count = new int[4];
		st = new StringTokenizer(br.readLine());
		for (int index = 0; index < 4; index++) {
			count[index] = Integer.parseInt(st.nextToken());
		}

		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
	}
}
