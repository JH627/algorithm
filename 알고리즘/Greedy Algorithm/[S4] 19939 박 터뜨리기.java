import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_19939_박터뜨리기 {

	static BufferedReader br;
	static StringTokenizer st;

	static int ballCount, teamCount;

	public static void main(String[] args) throws IOException {
		init();

		System.out.print(getMaxDiff());
	}

	static int getMaxDiff() {
		for (int team = 1; team <= teamCount; team++) {
			ballCount -= team;
			if (ballCount < 0) {
				return -1;
			}
		}

		return (ballCount % teamCount > 0) ? teamCount : teamCount - 1;
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		ballCount = Integer.parseInt(st.nextToken());
		teamCount = Integer.parseInt(st.nextToken());
	}
}
