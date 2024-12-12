import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static Long N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Long.parseLong(br.readLine());

        System.out.print(binSearch());
    }

    static long binSearch() {
        long l = 0;
        long r = N;
        long ret = 0;

        while (l <= r) {
            long mid = (l + r) / 2;

            if (Math.pow(mid, 2) >= N) {
                ret = mid;
                r = mid - 1;
            }
            else {
                l = mid + 1;
            }
        }
        return ret;
    }

}
