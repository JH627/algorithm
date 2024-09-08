import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        int l = 0;
        int r = 0;
        int sum = 0;
        int cnt = 0;

        while (l <= N) {
            while (++r <= N) {
                sum += r;
                if (sum == N) {
                    cnt++;
                }
                if (sum >= N) {
                    break;
                }
            }

            while (++l < N) {
                sum -= l;
                if (sum == N) {
                    cnt++;
                }
                if (sum <= N) {
                    break;
                }
            }
        }

        System.out.println(cnt);
    }
}
