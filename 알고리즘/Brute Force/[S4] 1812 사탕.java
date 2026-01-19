import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1812_사탕 {

	static BufferedReader br;
	static StringBuilder sb;

	static int studentCount;
	static int[] candy;

	public static void main(String[] args) throws IOException {
		init();
		findOriginalCandy();
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		studentCount = Integer.parseInt(br.readLine());
		candy = new int[studentCount];
		for (int student = 0; student < studentCount; student++) {
			candy[student] = Integer.parseInt(br.readLine());
		}
	}

	static void findOriginalCandy() {
		int sum = 0;
		for (int student = 0; student < studentCount; student++) {
			if (student % 2 == 0) {
				sum += candy[student];
			}
			else {
				sum -= candy[student];
			}
		}

		int[] originalCandy = new int[studentCount];
		originalCandy[0] = sum / 2;
		for (int student = 1; student < studentCount; student++) {
			originalCandy[student] = candy[student - 1] - originalCandy[student - 1];
		}

		sb = new StringBuilder();
		for (int student = 0; student < studentCount; student++) {
			sb.append(originalCandy[student]).append("\n");
		}
		System.out.print(sb);
	}
}
