import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_14405_피카츄 {

	static BufferedReader br;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();

		s = s.replaceAll("pi", " ");
		s = s.replaceAll("ka", " ");
		s = s.replaceAll("chu", " ");
		s = s.replaceAll(" ", "");

		System.out.print((s.isEmpty() ? "YES" : "NO"));
	}
}
