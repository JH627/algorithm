import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    static Stack<Character> s;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        s = new Stack<>();

        int ans = 0;
        int len = str.length();
        for (int i = 0; i < len; i++) {
            if (str.charAt(i) == '(') {
                s.push('(');
            }
            else {
                s.pop();
                ans += (str.charAt(i - 1) == '(') ? s.size() : 1;
            }
        }

        System.out.println(ans);
    }
}