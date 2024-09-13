import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static double[] arr;
    static double MAX = Double.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new double[N];
        for (int n = 0; n < N; n++) {
            arr[n] = Double.parseDouble(br.readLine());
        }

        double now = 1;
        for (int i = 0; i < N; i++) {
            now *= arr[i];
            if (now < arr[i]) {
                now = arr[i];
            }
            MAX = Math.max(MAX, now);
        }

        System.out.printf("%.3f", MAX);
    }
}
