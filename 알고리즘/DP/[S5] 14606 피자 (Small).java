import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];
        for (int i = 2; i < N + 1; i++) {
            arr[i] = (i - 1) + arr[i - 1];
        }
        System.out.print(arr[N]);
    }
}

