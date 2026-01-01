import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1339_단어수학 {

	static BufferedReader br;

	static int wordCount;
	static int[] score;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getMaxScore());
	}

	static int getMaxScore() {
		Arrays.sort(score);

		int sum = 0;
		int num = 9;
		for (int index = 25; index >= 0; index--) {
			if (score[index] == 0) {
				break;
			}
			sum += score[index] * num;
			num--;
		}

		return sum;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		wordCount = Integer.parseInt(br.readLine());

		score = new int[26];
		for (int word = 0; word < wordCount; word++) {
			char[] line = br.readLine().toCharArray();
			for (int index = 0; index < line.length; index++) {
				score[line[index] - 'A'] += (int)Math.pow(10, line.length - 1 - index);
			}
		}
	}
}
