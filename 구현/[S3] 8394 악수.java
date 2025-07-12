import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_8394_악수 {

	static BufferedReader br;

	static int[] count;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		int max = Integer.parseInt(br.readLine());
		count = new int[max + 3];
		count[0] = 1; count[1] = 1;
		for (int num = 2; num <= max; num++) {
			count[num] = (count[num - 1] + count[num - 2]) % 10;
		}

		System.out.print(count[max]);
	}
}
