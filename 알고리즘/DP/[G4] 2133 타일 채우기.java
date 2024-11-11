import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        if (N % 2 == 1) {
            System.out.print(0);
            return;
        }

        int[] arr = new int[N + 1];

        arr[0] = 1;
        arr[2] = 3;
        for (int i = 4; i < N + 1; i += 2) {
            arr[i] = 3 * arr[i - 2];
            for (int j = i - 4; j >= 0; j -= 2) {
                arr[i] += arr[j] * 2;
            }
        }

        System.out.print(arr[N]);
    }
}
