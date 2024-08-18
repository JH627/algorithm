import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        System.out.println(solve());
    }

    static int solve() {
        int ans = 0;
        for (int n = 0; n < N; n++) {
            int l = 0;
            int r = N - 1;
            while (l < r) {
                if (l == n) {
                    l++;
                }
                if (r == n) {
                    r--;
                }
                if (l == r) {
                    break;
                }
                int sum = arr[l] + arr[r];

                if (sum > arr[n]) {
                    r--;
                }
                else if (sum < arr[n]) {
                    l++;
                }
                else {
                    ans++;
                    break;
                }
            }
        }
        return ans;
    }
}
