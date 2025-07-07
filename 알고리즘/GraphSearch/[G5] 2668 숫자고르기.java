import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_2668_숫자고르기 {

	static BufferedReader br;
	static StringBuilder sb;

	static int numCount;
	static int[] num;
	static boolean[] visited;
	static ArrayList<Integer> answer;

	public static void main(String[] args) throws IOException {
		init();
		getMaxCycle();
		printAnswer();
	}

	static void getMaxCycle() {
		for (int index = 1; index <= numCount; index++) {
			visited[index] = true;
			findCycle(index, index);
			visited[index] = false;
		}
	}

	static void findCycle(int now, int start) {
		if (num[now] == start) {
			answer.add(start);
		}

		if (!visited[num[now]]) {
			visited[num[now]] = true;
			findCycle(num[now], start);
			visited[num[now]] = false;
		}
	}

	static void printAnswer() {
		sb.append(answer.size()).append('\n');
		for (Integer num : answer) {
			sb.append(num).append('\n');
		}
		System.out.print(sb);
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		numCount = Integer.parseInt(br.readLine());
		num = new int[numCount + 1];
		visited = new boolean[numCount + 1];
		answer = new ArrayList<>();
		for (int index = 1; index <= numCount; index++) {
			num[index] = Integer.parseInt(br.readLine());
		}
	}
}
