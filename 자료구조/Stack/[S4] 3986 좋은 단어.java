import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    static int N;
    static String s;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        int cnt = 0;
        for (int n = 0; n < N; n++) {
            s = br.readLine();
            if (check()) {
                cnt++;
            }
        }
        System.out.print(cnt);
    }

    static boolean check() {
        Stack<Character> stack = new Stack<>();
        int len = s.length();
        stack.add(s.charAt(0));
        for (int i = 1; i < len; i++) {
            if (stack.isEmpty()) {
                stack.push(s.charAt(i));
            }
            else {
                if (stack.peek() == s.charAt(i)) {
                    stack.pop();
                }
                else {
                    stack.push(s.charAt(i));
                }
            }
        }

        return stack.isEmpty();
    }
}
