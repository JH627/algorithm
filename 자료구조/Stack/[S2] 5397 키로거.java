import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_5397_키로거 {

	static BufferedReader br;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int testCase = 0; testCase < TC; testCase++) {
			String s = br.readLine();
			sb.append(getPassword(s)).append("\n");
		}
		System.out.print(sb);
	}

	static String getPassword(String s) {
		Stack<Character> left = new Stack<>();
		Stack<Character> right = new Stack<>();

		int len = s.length();
		for (int index = 0; index < len; index++) {
			char c = s.charAt(index);

			switch (c) {
				case '<':
					if (!left.empty()) {
						char p = left.pop();
						right.push(p);
					}
					break;
				case '>':
					if (!right.empty()) {
						char p = right.pop();
						left.push(p);
					}
					break;
				case '-':
					if (!left.empty()) {
						left.pop();
					}
					break;
				default:
					left.push(c);
			}
		}

		StringBuilder sb = new StringBuilder();
		while (!left.isEmpty()) {
			sb.append(left.pop());
		}
		sb.reverse();
		while (!right.isEmpty()) {
			sb.append(right.pop());
		}
		return sb.toString();
	}

}
