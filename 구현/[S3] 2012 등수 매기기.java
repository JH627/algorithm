import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2012_등수매기기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int manCount = Integer.parseInt(br.readLine());
		int[] think = new int[manCount];
		for (int man = 0; man < manCount; man++) {
			think[man] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(think);

		long sum = 0;
		for (int man = 0; man < manCount; man++) {
			sum += Math.abs(think[man] - (man + 1));
		}
		System.out.print(sum);
	}
}
