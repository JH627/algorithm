import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_14729_칠무해 {

	static int elementCount;
	static double[] score;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		elementCount = Integer.parseInt(br.readLine());

		score = new double[elementCount];
		for (int elementIndex = 0; elementIndex < elementCount; elementIndex++) {
			score[elementIndex] = Double.parseDouble(br.readLine());
		}

		Arrays.sort(score);

		StringBuilder sb = new StringBuilder();
		for (int elementIndex = 0; elementIndex < Math.min(7, elementCount); elementIndex++) {
			sb.append(String.format("%.3f\n", score[elementIndex]));
		}

		System.out.println(sb);
	}
}
