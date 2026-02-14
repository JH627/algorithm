import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_17952_과제는끝나지않아 {

	static BufferedReader br;
	static StringTokenizer st;

	static class Homework {
		int remainTime;
		int result;

		public Homework(int remainTime, int result) {
			this.remainTime = remainTime;
			this.result = result;
		}
	}

	static int timeLimit;

	public static void main(String[] args) throws IOException {
		init();
		System.out.print(getResult());
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		timeLimit = Integer.parseInt(br.readLine());
	}

	static int getResult() throws IOException {
		int resultSum = 0;

		Stack<Homework> homeworks = new Stack<>();
		for (int time = 0; time < timeLimit; time++) {
			st = new StringTokenizer(br.readLine());

			int operation = Integer.parseInt(st.nextToken());
			switch (operation) {
				case 1:
					int result = Integer.parseInt(st.nextToken());
					int solveTime = Integer.parseInt(st.nextToken());
					if (solveTime == 1) {
						resultSum += result;
						break;
					}
					homeworks.add(new Homework(solveTime - 1, result));
					break;
				case 0:
					if (homeworks.isEmpty()) {
						break;
					}
					homeworks.peek().remainTime -= 1;
					if (homeworks.peek().remainTime == 0) {
						resultSum += homeworks.peek().result;
						homeworks.pop();
					}
			}
		}

		return resultSum;
	}
}
