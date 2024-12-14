import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];
        arr[1] = 1;

        for (int n = 2; n < N + 1; n++) {
            int min = Integer.MAX_VALUE;
            for (int i = 1; i * i <= n; i++) {
                int before = n - i * i;
                min = Math.min(min, arr[before]);
            }

            arr[n] = min + 1;
        }

        System.out.print(arr[N]);
    }
}
