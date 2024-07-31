import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    static String a, b;
    static int[][] dp;
    static Stack<Character> stack;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        a = br.readLine();
        b = br.readLine();

        int alen = a.length();
        int blen = b.length();

        dp = new int[alen + 1][blen + 1];

        for (int i = 1; i < alen + 1; i++) {
            for (int j = 1; j < blen + 1; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        stack = new Stack<>();

        trace();

        StringBuilder sb = new StringBuilder();
        sb.append(dp[alen][blen]).append("\n");
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        System.out.println(sb);
    }

    static void trace() {
        int i = a.length();
        int j = b.length();

        while (i > 0 && j > 0) {
            if (dp[i][j] == dp[i - 1][j]) {
                i--;
            }
            else if (dp[i][j] == dp[i][j - 1]) {
                j--;
            }
            else {
                stack.push(a.charAt(i - 1));
                i--;
                j--;
            }
        }
    }
}
