import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1205_등수구하기 {

	static BufferedReader br;
	static StringTokenizer st;

	static int manCount, newScore, listSize;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());

		manCount = Integer.parseInt(st.nextToken());
		newScore = Integer.parseInt(st.nextToken());
		listSize = Integer.parseInt(st.nextToken());

		if (manCount == 0) {
			System.out.print(1);
			return;
		}

		int highScoreCount = 0;
		int sameScoreCount = 0;
		st = new StringTokenizer(br.readLine());
		for (int manIndex = 0; manIndex < manCount; manIndex++) {
			int score = Integer.parseInt(st.nextToken());
			if (score > newScore) {
				if (++highScoreCount == listSize) {
					System.out.print(-1);
					return;
				}
			}
			else if (score == newScore) {
				sameScoreCount++;
			}

			if (highScoreCount + sameScoreCount == listSize) {
				System.out.print(-1);
				return;
			}
		}

		System.out.print(highScoreCount + 1);
	}
}
