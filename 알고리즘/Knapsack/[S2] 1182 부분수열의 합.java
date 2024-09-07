import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, S;
    static int[] arr;
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        knap(0, 0);
        System.out.print((S == 0) ? cnt - 1 : cnt);
    }

    static void knap(int idx, int sum) {
        if (idx == N) {
            if (sum == S) {
                cnt++;
            }
            return;
        }
        if (sum > S && arr[idx] > 0) {
            return;
        }
        knap(idx + 1, sum + arr[idx]);
        knap(idx + 1, sum);
    }
}
