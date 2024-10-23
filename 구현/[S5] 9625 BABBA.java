import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        K = Integer.parseInt(br.readLine());

        int a = 0;
        int b = 0;

        if (K >= 1) {
            b = 1;
        }

        for (int k = 1; k < K; k++) {
            int temp = b;
            b = a + b;
            a = temp;
        }

        System.out.printf("%d %d", a, b);
    }
}
