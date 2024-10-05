import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {

    static int N;
    static BigInteger[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        dp = new BigInteger[N + 2];

        dp[0] = BigInteger.valueOf(0);
        dp[1] = BigInteger.valueOf(1);
        for (int i = 2; i < N + 1; i++) {
            dp[i] = dp[i - 1].add(dp[i - 2]);
        }

        System.out.print(dp[N]);
    }
}
