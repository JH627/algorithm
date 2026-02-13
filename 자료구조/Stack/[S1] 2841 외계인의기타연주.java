import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_2841_외계인의기타연주 {

	static BufferedReader br;
	static StringTokenizer st;

	static int soundCount, fretCount;
	static Stack<Integer>[] status;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getMoveCount());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		status = new Stack[7];

		st = new StringTokenizer(br.readLine());
		soundCount = Integer.parseInt(st.nextToken());
		fretCount = Integer.parseInt(st.nextToken());

		for (int line = 0; line < 7; line++) {
			status[line] = new Stack<>();
		}
	}

	static int getMoveCount() throws IOException {
		int moveCount = 0;

		for (int sound = 0; sound < soundCount; sound++) {
			st = new StringTokenizer(br.readLine());

			int line = Integer.parseInt(st.nextToken());
			int fret = Integer.parseInt(st.nextToken());

			while (!status[line].isEmpty() && status[line].peek() > fret) {
				status[line].pop();
				moveCount++;
			}

			if (status[line].isEmpty() || status[line].peek() < fret) {
				status[line].push(fret);
				moveCount++;
			}
		}

		return moveCount;
	}
}
