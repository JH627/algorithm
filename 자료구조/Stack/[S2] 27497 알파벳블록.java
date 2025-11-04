import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_27497_알파벳블록 {

    static BufferedReader br;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        Stack<Character> left = new Stack<>();
        Stack<Character> right = new Stack<>();
        Stack<Integer> operation = new Stack<>();

        int queryCount = Integer.parseInt(br.readLine());
        for (int query = 0; query < queryCount; query++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            char c;
            switch (op) {
                case 1:
                    c = st.nextToken().charAt(0);
                    operation.add(1);
                    right.push(c);
                    break;
                case 2:
                    c = st.nextToken().charAt(0);
                    operation.add(2);
                    left.push(c);
                    break;
                case 3:
                    if (!operation.isEmpty()) {
                        int popOp = operation.pop();
                        if (popOp == 1) {
                            right.pop();
                        }
                        else {
                            left.pop();
                        }
                    }
                    break;
            }
        }

        if (left.isEmpty() && right.isEmpty()) {
            System.out.print("0");
            return;
        }

        StringBuilder sb = new StringBuilder();
        while (!left.isEmpty()) {
            sb.append(left.pop());
        }
        System.out.print(sb);

        sb = new StringBuilder();
        while (!right.isEmpty()) {
            sb.append(right.pop());
        }
        System.out.print(sb.reverse());
    }
}
