import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static long[] arr, remain;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new long[N + 1];
        remain = new long[M];

        long cnt = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            arr[i] = Long.parseLong(st.nextToken());
            arr[i] = (arr[i] + arr[i - 1]) % M;
            if (arr[i] % M == 0) {
                cnt++;
            }
            remain[(int) arr[i]]++;
        }

        for (int i = 0; i < M; i++) {
            if (remain[i] > 1) {
                cnt += (remain[i] * (remain[i] - 1) / 2);
            }
        }

        System.out.println(cnt);
    }
}
