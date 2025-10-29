import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1788_피보나치수의확장 {

    static BufferedReader br;

    static int number;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        init();
        findFib();
    }
    
    static void findFib() {
        if (number < 0) {
            System.out.printf("%d\n%d", (-number % 2 == 0) ? -1 : 1, dp[-number]);
        }
        else if (number == 0) {
            System.out.print("0\n0");
        }
        else {
            System.out.printf("1\n%d", dp[number]);
        }
    }

    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        
        number = Integer.parseInt(br.readLine());
        
        int num = (number < 0) ? -number : number;
        
        dp = new int[num + 2];
        dp[0] = 0;
        dp[1] = 1;
        
        for (int n = 2; n < num + 2; n++) {
            dp[n] = (dp[n - 1] + dp[n - 2]) % 1000000000;
        }
    }
}
