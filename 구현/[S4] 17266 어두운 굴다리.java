import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17266_어두운굴다리 {

	static BufferedReader br;
	static StringTokenizer st;

	static int wayLength, towerCount;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		wayLength = Integer.parseInt(br.readLine());
		towerCount = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		int now = 0, prev = 0;
		int max = 0;
		for (int tower = 0; tower < towerCount; tower++) {
			now = Integer.parseInt(st.nextToken());
			if (tower == 0) {
				max = now;
			}
			else {
				int gap = now - prev;
				max = Math.max(max, (gap + 1) / 2);
			}

			prev = now;
		}

		max = Math.max(max, wayLength - now);

		System.out.print(max);
	}

}
