import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9658_돌게임4 {

	static BufferedReader br;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		int rockCount = Integer.parseInt(br.readLine());
		System.out.print((rockCount % 7 == 1 || rockCount % 7 == 3) ? "CY": "SK");
	}
}
