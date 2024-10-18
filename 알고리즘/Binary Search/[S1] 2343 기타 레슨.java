import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];

        int max = 0;
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            arr[n] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[n]);
        }

        int l = max;
        int r = N * 10000;
        while (l < r) {
            int mid = (l + r) / 2;
            if (isPossible(mid)) {
                r = mid;
            }
            else {
                l = mid + 1;
            }
        }

        System.out.print(r);
    }

    static boolean isPossible(int max) {
        int cnt = 1;
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += arr[i];
            if (sum <= max) {
                continue;
            }
            i--;
            sum = 0;
            if (++cnt > M) {
                return false;
            }
        }
        return true;
    }
}
