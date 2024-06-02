import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N + 1];
        dp = new int[N + 1];

        int ans = 0;
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 1; i <= N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            for(int j = 0 ; j < i; j++){
                if(arr[j] < arr[i]){
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                    ans = Math.max(dp[i], ans);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();

        sb.append(ans).append("\n");
        for(int i = N; i >= 1; i--){
            if(ans == dp[i]) {
                stack.push(arr[i]);
                ans--;
            }
        }

        while (!stack.isEmpty()){
            sb.append(stack.pop()).append(" ");
        }

        System.out.println(sb);
    }
}