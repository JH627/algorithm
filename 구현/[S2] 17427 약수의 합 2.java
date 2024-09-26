import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        long sum = 1;
        for (int i = 2; i <= N; i++) {
            sum += (long) i * (N / i) + 1;
        }

        System.out.print(sum);
    }
}
