import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_1863_스카이라인쉬운거 {

	static BufferedReader br;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		int towerCount = Integer.parseInt(br.readLine());
		Stack<Integer> stack = new Stack<>();

		int minTowerCount = 0;
		for (int tower = 0; tower < towerCount; tower++) {
			st = new StringTokenizer(br.readLine());

			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			while (!stack.isEmpty() && stack.peek() > y) {
				minTowerCount++;
				stack.pop();
			}
			if (!stack.isEmpty() && stack.peek() == y) {
				continue;
			}

			stack.push(y);
		}

		while (!stack.isEmpty()) {
			if (stack.pop() > 0) {
				minTowerCount++;
			}
		}

		System.out.print(minTowerCount);
	}
}
