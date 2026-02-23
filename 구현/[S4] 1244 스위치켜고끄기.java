import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1244_스위치켜고끄기 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int elementCount, queryCount;
	static boolean[] status;

	public static void main(String[] args) throws IOException {
		init();
		changeStatus();
		printStatus();
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		elementCount = Integer.parseInt(br.readLine());
		status = new boolean[elementCount];

		st = new StringTokenizer(br.readLine());
		for (int element = 0; element < elementCount; element++) {
			int flag = Integer.parseInt(st.nextToken());
			if (flag == 1) {
				status[element] = true;
			}
		}

		queryCount = Integer.parseInt(br.readLine());
	}

	static void changeStatus() throws IOException {
		for (int query = 0; query < queryCount; query++) {
			st = new StringTokenizer(br.readLine());
			int user = Integer.parseInt(st.nextToken());
			int targetIndex = Integer.parseInt(st.nextToken()) - 1;

			switch (user) {
				case 1:
					toggleMultiples(targetIndex);
					break;
				case 2:
					toggleSymmetric(targetIndex);
					break;
			}
		}
	}

	static void toggleMultiples(int targetIndex) {
		int step = targetIndex + 1;
		for (int index = step - 1; index < elementCount; index += step) {
			status[index] = !status[index];
		}
	}

	static void toggleSymmetric(int targetIndex) {
		status[targetIndex] = !status[targetIndex];

		int l = targetIndex - 1;
		int r = targetIndex + 1;

		while (l >= 0 && r < elementCount) {
			if (status[l] == status[r]) {
				status[l] = !status[l];
				status[r] = !status[r];
			}
			else {
				break;
			}

			l--;
			r++;
		}
	}

	static void printStatus() {
		sb = new StringBuilder();
		for (int index = 0; index < elementCount; index++) {
			sb.append(status[index] ? "1" : "0").append(" ");
			if ((index + 1) % 20 == 0) {
				sb.append("\n");
			}
		}
		System.out.print(sb);
	}
}
