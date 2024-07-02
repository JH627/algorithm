import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static long[] arr, sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new long[N + 1];
        sum = new long[N + 1];

        long ans = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            arr[i] = Long.parseLong(st.nextToken());
            sum[i] += sum[i - 1] + arr[i];
            ans += sum[i - 1] * arr[i];
        }

        System.out.println(ans);
    }
}
