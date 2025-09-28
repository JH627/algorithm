import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_4889_안정적인문자열 {

	static BufferedReader br;
	static StringBuilder sb;

	static String line;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int testCase = 0;
		while (++testCase > 0) {
			line = br.readLine();
			if (line.startsWith("-")) {
				break;
			}
			int count = findMinCount();
			sb.append(testCase).append(". ").append(count).append("\n");
		}

		System.out.print(sb);
	}

	static int findMinCount() {
		Stack<Character> stack = new Stack<>();
		for (Character c : line.toCharArray()) {
			if (c == '}') {
				if (!stack.isEmpty() && stack.peek() == '{') {
					stack.pop();
				}
				else {
					stack.push(c);
				}
			}
			else if (c == '{') {
				stack.push(c);
			}
		}

		int count = 0;
		while (!stack.isEmpty()) {
			if (stack.peek() == '{') {
				stack.pop();
				count++;
				if (stack.peek() == '}') {
					count++;
				}
			}
			else {
				count++;
				stack.pop();
			}
			stack.pop();
		}

		return count;
	}

}
