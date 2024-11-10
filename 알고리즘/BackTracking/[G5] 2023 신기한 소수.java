import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        bt(0, 0);

        System.out.print(sb);
    }

    static void bt(int idx, int num) {
        if (idx == N) {
            sb.append(num).append('\n');
            return;
        }

        for (int i = 0; i < 10; i++) {
            int next = num * 10 + i;
            if (isPrime(next)) {
                bt(idx + 1, next);
            }
        }
    }

    static boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }
        for (int i = 2; i * i <= num; i++) {
            if (num % i ==0) {
                return false;
            }
        }
        return true;
    }
}
