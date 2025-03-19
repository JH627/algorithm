import java.io.*;
import java.util.*;

public class BOJ_32931_자석놀이 {
    
    static int N;
    static long[][] arr, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new long[2][N];
        dp = new long[2][N];

        StringTokenizer st;
        for (int i = 0; i < 2; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Long.parseLong(st.nextToken());
            }
        }

        // 초기값 설정
        dp[0][0] = Math.max(arr[0][0], arr[0][0] + arr[1][0]); 
        dp[1][0] = arr[0][0] + arr[1][0];

        // DP 계산
        for (int j = 1; j < N; j++) {
            long a, b, c;

            // 위쪽에서 진행하는 경우
            a = dp[0][j - 1] + arr[0][j];
            b = dp[0][j - 1] + arr[0][j] + arr[1][j]; 
            c = dp[1][j - 1] + arr[0][j] + arr[1][j];
            dp[0][j] = Math.max(Math.max(a, b), c);

            // 아래쪽에서 진행하는 경우
            a = dp[1][j - 1] + arr[1][j];
            b = dp[1][j - 1] + arr[1][j] + arr[0][j];
            c = dp[0][j - 1] + arr[0][j] + arr[1][j];
            dp[1][j] = Math.max(Math.max(a, b), c);
        }

        // 정답 출력
        System.out.println(dp[1][N - 1]);
    }
}
