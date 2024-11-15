import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr, dp1, dp2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        arr = new int[N];
        dp1 = new int[N];
        dp2 = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            arr[n] = Integer.parseInt(st.nextToken());
        }

        int max = arr[0];
        dp2[0] = arr[0];
        for (int n = 1; n < N; n++) {
            dp1[n] = Math.max(dp1[n - 1] + arr[n], dp2[n - 1]);
            dp2[n] = Math.max(dp2[n - 1] + arr[n], arr[n]);
            max = Math.max(max, dp1[n]);
            max = Math.max(max, dp2[n]);
        }

        System.out.print(max);
    }
}
