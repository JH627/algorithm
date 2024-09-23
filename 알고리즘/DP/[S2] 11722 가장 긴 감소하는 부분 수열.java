import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        arr = new int[N];
        dp = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            arr[n] = Integer.parseInt(st.nextToken());
        }

        dp[0] = 1;
        int ans = 1;
        for (int n = 1; n < N; n++) {
            int max = 0;
            for (int i = n - 1; i >= 0; i--) {
                if (arr[n] < arr[i]) {
                    max = Math.max(max, dp[i]);
                }
            }
            dp[n] = max + 1;
            ans = Math.max(ans, dp[n]);
        }

        System.out.print(ans);
    }
}
