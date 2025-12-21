import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_15736_청기백기 {

	static BufferedReader br;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		int flagCount = Integer.parseInt(br.readLine());
		System.out.print((int) Math.sqrt(flagCount));
	}
}
