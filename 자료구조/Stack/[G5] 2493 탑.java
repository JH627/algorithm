import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        Stack<int[]> stack = new Stack<>();
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            int h = Integer.parseInt(st.nextToken());

            if (stack.isEmpty()) {
                sb.append("0 ");
                stack.push(new int[]{h, n + 1});
            }
            else {
                while (true) {
                    if (stack.isEmpty()) {
                        sb.append("0 ");
                        stack.push(new int[]{h, n + 1});
                        break;
                    }

                    int[] top = stack.peek();

                    if (top[0] <= h) {
                        stack.pop();
                    }
                    else {
                        sb.append(top[1]).append(" ");
                        stack.push(new int[]{h, n + 1});
                        break;
                    }
                }
            }
        }

        System.out.print(sb);
    }
}
