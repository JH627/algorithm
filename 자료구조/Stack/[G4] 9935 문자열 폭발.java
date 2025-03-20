import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_9935_문자열폭발 {

	static String str, pattern;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine();
		pattern = br.readLine();
		
		int patternLength = pattern.length();
		Stack<Character> stack = new Stack<>();
		int len = str.length();
		for (int index = 0; index < len; index++) {
			stack.add(str.charAt(index));
			if (stack.size() >= patternLength) {
				boolean possible = true;
				for (int i = 0; i < patternLength; i++) {
					if (stack.get(stack.size() - patternLength + i) != pattern.charAt(i)) {
						possible = false;
						break;
					}
				}
						
				if (possible) {
					for (int i = 0; i < patternLength; i++) {
						stack.pop();
					}
				}
			}
		}
		
		if (stack.isEmpty()) {
			System.out.print("FRULA");
			return;
		}
		
		StringBuilder sb = new StringBuilder();
		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		System.out.print(sb.reverse());
	}

}
