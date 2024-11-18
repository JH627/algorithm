import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N, M;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        String s = br.readLine();

        int cnt = 0;
        dp = new int[M + 1];
        for (int i = 1; i < M - 1; i++) {
            if (s.charAt(i) != 'O') {
                continue;
            }
            if (s.charAt(i - 1) == 'I' && s.charAt(i + 1) == 'I') {
                dp[i + 1] = dp[i - 1] + 1;
                if (dp[i + 1] >= N) {
                    cnt++;
                    i++;
                }
            }
        }

        System.out.print(cnt);
    }

}
