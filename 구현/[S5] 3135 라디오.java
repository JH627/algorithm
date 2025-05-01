import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3135_라디오 {

	static BufferedReader br;
	static StringTokenizer st;

	static int start, end;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());

		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());

		int min = Math.abs(end - start);

		int pre = Integer.parseInt(br.readLine());
		for (int i = 0; i < pre; i++) {
			int fixed = Integer.parseInt(br.readLine());
			min = Math.min(Math.abs(end - fixed) + 1, min);
		}

		System.out.println(min);
	}
}
