import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15702_중간고사채점 {

	static BufferedReader br;
	static StringTokenizer st;

	static int problemCount, userCount;
	static int[] problemScore;

	public static void main(String[] args) throws IOException {
		init();
		findMaxScore();
	}

	static void findMaxScore() throws IOException {
		int maxScore = Integer.MIN_VALUE;
		int maxUser = Integer.MAX_VALUE;

		for (int user = 0; user < userCount; user++) {
			st = new StringTokenizer(br.readLine());
			int userNumber = Integer.parseInt(st.nextToken());
			int scoreSum = 0;
			for (int problem = 0; problem < problemCount; problem++) {
				if (st.nextToken().charAt(0) == 'O') {
					scoreSum += problemScore[problem];
				}
			}

			if (scoreSum == maxScore) {
				if (maxUser > userNumber) {
					maxUser = userNumber;
				}
			}
			else if (scoreSum > maxScore) {
				maxScore = scoreSum;
				maxUser = userNumber;
			}
		}

		System.out.printf("%d %d", maxUser, maxScore);
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		problemCount = Integer.parseInt(st.nextToken());
		userCount = Integer.parseInt(st.nextToken());

		problemScore = new int[problemCount];
		st = new StringTokenizer(br.readLine());
		for (int problem = 0; problem < problemCount; problem++) {
			problemScore[problem] = Integer.parseInt(st.nextToken());
		}
	}

}
