import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        int sLen = s.length();

        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < sLen; i++) {
            if (Character.isAlphabetic(s.charAt(i))) {
                sb.append(s.charAt(i));
            }
            else if (s.charAt(i) == '(') {
                stack.push(s.charAt(i));
            }
            else if (s.charAt(i) == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    sb.append(stack.pop());
                }
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            }
            else {
                while (!stack.isEmpty() && priority(stack.peek()) >= priority(s.charAt(i))) {
                    sb.append(stack.pop());
                }
                stack.push(s.charAt(i));
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        System.out.print(sb);
    }

    static int priority(char c) {
        if (c == '/' || c == '*') {
            return 2;
        }
        else if (c == '+' || c == '-') {
            return 1;
        }
        return 0;
    }
}
