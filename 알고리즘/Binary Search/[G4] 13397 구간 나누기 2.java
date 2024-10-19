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
        int l = 0;
        int r = Integer.MIN_VALUE;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            r = Math.max(r, arr[i]);
        }

        while (l < r) {
            int mid = (l + r) / 2;
            if (check(mid)) {
                r = mid;
            }
            else {
                l = mid + 1;
            }
        }

        System.out.print(r);
    }

    static boolean check(int limit) {
        int cnt = 1;
        int min = arr[0];
        int max = arr[0];
        for (int i = 0; i < N; i++) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
            if ((max - min) <= limit) {
                continue;
            }
            min = arr[i];
            max = arr[i];
            i--;
            if (++cnt > M) {
                return false;
            }
        }
        return true;
    }
}
