import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N, K;
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[] arr = new int[K + 1];
        Arrays.fill(arr, 10000000);

        arr[0] = 0;
        for (int n = 0; n < N; n++) {
            int num = Integer.parseInt(br.readLine());
            for (int i = num; i < K + 1; i++) {
                arr[i] = Math.min(arr[i - num] + 1, arr[i]);
            }
        }

        System.out.print((arr[K] == 10000000) ? -1 : arr[K]);
    }
}
