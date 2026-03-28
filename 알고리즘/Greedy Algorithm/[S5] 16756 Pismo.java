import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16756_Pismo {

	static BufferedReader br;
	static StringTokenizer st;

	static int numberCount;
	static int[] numbers;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getMinValue());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		numberCount = Integer.parseInt(br.readLine());
		numbers = new int[numberCount];
		st = new StringTokenizer(br.readLine());
		for (int number = 0; number < numberCount; number++) {
			numbers[number] = Integer.parseInt(st.nextToken());
		}
	}

	static int getMinValue() {
		int min = Integer.MAX_VALUE;
		for (int number = 1; number < numberCount; number++) {
			int diff = Math.abs(numbers[number] - numbers[number - 1]);
			min = Math.min(min, diff);
		}

		return min;
	}
}
