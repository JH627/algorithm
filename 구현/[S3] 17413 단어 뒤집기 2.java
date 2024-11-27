import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        String s = new BufferedReader(new InputStreamReader(System.in)).readLine();
        Stack<Character> stack = new Stack<>();

        StringBuilder sb = new StringBuilder();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == '<') {
                while (s.charAt(i) != '>') {
                    sb.append(s.charAt(i));
                    i++;
                }
                sb.append(">");
            }
            else {
                while (i < len && s.charAt(i) != ' ' && s.charAt(i) != '<') {
                    stack.push(s.charAt(i));
                    i++;
                }
                while (!stack.isEmpty()) {
                    sb.append(stack.pop());
                }
                if (i < len && s.charAt(i) == ' ') {
                    sb.append(" ");
                }
                else {
                    i--;
                }
            }
        }

        System.out.print(sb);

    }
}
