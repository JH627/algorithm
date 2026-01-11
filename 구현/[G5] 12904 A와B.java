import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_12904_A와B {

	static BufferedReader br;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder a = new StringBuilder(br.readLine());
		StringBuilder b = new StringBuilder(br.readLine());

		while (a.length() != b.length()) {
			if (b.charAt(b.length() - 1) == 'A') {
				b.delete(b.length() - 1, b.length());
			}
			else {
				b.delete(b.length() - 1, b.length());
				b.reverse();
			}
		}

		System.out.print((a.toString().equals(b.toString())) ? "1" : "0");
	}
}
