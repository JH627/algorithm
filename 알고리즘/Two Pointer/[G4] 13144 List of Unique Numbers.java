import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr;
    static final int SIZE = 100001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            arr[n] = Integer.parseInt(st.nextToken());
        }

        long ans = N;
        int l = 0;
        int r = 1;
        boolean[] checked = new boolean[SIZE];
        checked[arr[l]] = true;
        while (r < N) {
            if (checked[arr[r]]) {
                checked[arr[l]] = false;
                l++;
            }
            else {
                checked[arr[r]] = true;
                ans += r - l;
                r++;
            }
        }

        System.out.print(ans);
    }
}
