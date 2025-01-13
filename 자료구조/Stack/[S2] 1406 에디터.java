import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        int M = Integer.parseInt(br.readLine());

        Stack<Character> left = new Stack<>();
        Stack<Character> right = new Stack<>();

        int len = s.length();
        for (int i = 0; i < len; i++) {
            left.push(s.charAt(i));
        }

        StringTokenizer st;
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            switch (st.nextToken()) {
                case "L":
                    if (left.empty()) {
                        continue;
                    }
                    Character l = left.pop();
                    right.push(l);
                    break;
                case "D":
                    if (right.empty()) {
                        continue;
                    }
                    Character r = right.pop();
                    left.push(r);
                    break;
                case "B":
                    if (left.empty()) {
                        continue;
                    }
                    left.pop();
                    break;
                case "P":
                    left.push(st.nextToken().charAt(0));
                    break;
            }

        }

        StringBuilder sb = new StringBuilder();
        while (!left.empty()) {
            sb.append(left.pop());
        }
        sb.reverse();
        while(!right.empty()) {
            sb.append(right.pop());
        }
        System.out.print(sb);
    }
}
