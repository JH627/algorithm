import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1744_수묶기 {

	static BufferedReader br;

	static int numCount;
	static int[] numbers;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(findMax());
	}

	static int findMax() {
		Arrays.sort(numbers);

		int sum = 0;
		for (int num = 0; num < numCount; num++) {
			if (numbers[num] < 0) {
				if (num < numCount - 1) {
					if (numbers[num + 1] <= 0) {
						sum += numbers[num] * numbers[num + 1];
						num++;
					}
					else {
						sum += numbers[num];
					}
				}
				else {
					sum += numbers[num];
				}
			}
			else if (numbers[num] == 1) {
				sum++;
			}
			else if (numbers[num] > 1) {
				if ((numCount - num) % 2 == 0) {
					sum += numbers[num] * numbers[num + 1];
					num++;
				}
				else {
					sum += numbers[num];
				}
			}
		}
		return sum;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		numCount = Integer.parseInt(br.readLine());
		numbers = new int[numCount];
		for (int num = 0; num < numCount; num++) {
			numbers[num] = Integer.parseInt(br.readLine());
		}
	}

}
