import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static final int MAX = 1000001;

    static int T;
    static long[] arr, sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        arr = new long[MAX];
        sum = new long[MAX];
        Arrays.fill(arr, 1);

        for (int i = 2; i < MAX; i++) {
            for (int j = 1; i * j < MAX; j++) {
                arr[i * j] += i;
            }
        }

        for (int i = 1; i < MAX; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }

        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            sb.append(sum[n]).append("\n");
        }

        System.out.println(sb);
    }
}
