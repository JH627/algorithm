import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static final long mod = (long) 1e12;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long[] arr = new long[N + 1];
        arr[0] = 1;
        for (int n = 1; n <= N; n++) {
            arr[n] = arr[n - 1] * n;
            while (arr[n] % 10 == 0) {
                arr[n] /= 10;
            }
            arr[n] %= mod;
        }

        System.out.printf("%05d", arr[N] % 100000);
    }

}
