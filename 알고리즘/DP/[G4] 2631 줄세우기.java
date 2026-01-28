import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_2631_줄세우기 {

	static BufferedReader br;

	static int userCount;
	static int[] numbers;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(findMinSwapUserCount());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		userCount = Integer.parseInt(br.readLine());

		numbers = new int[userCount];
		for (int userIndex = 0; userIndex < userCount; userIndex++) {
			numbers[userIndex] = Integer.parseInt(br.readLine());
		}
	}

	static int findMinSwapUserCount() {
		ArrayList<Integer> users = new ArrayList<>();
		users.add(0);

		for (Integer userNumber : numbers) {
			if (users.get(users.size() - 1) < userNumber) {
				users.add(userNumber);
				continue;
			}

			int l = 0;
			int r = users.size() - 1;
			while (l < r) {
				int m = (l + r) / 2;
				if (users.get(m) < userNumber) {
					l = m + 1;
				}
				else {
					r = m;
				}
			}
			users.set(r, userNumber);
		}

		return userCount - users.size() + 1;
	}
}
